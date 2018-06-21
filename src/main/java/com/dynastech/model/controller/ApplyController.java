
package com.dynastech.model.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dynastech.base.common.service.impl.CommonServiceImpl;
import com.dynastech.base.global.Constants;
import com.dynastech.base.log.LogType;
import com.dynastech.base.util.DateUtil;
import com.dynastech.base.util.ExportExcelUtil;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.base.util.LogUtil;
import com.dynastech.common.service.IDictionaryService;
import com.dynastech.flow.entity.Procinst;
import com.dynastech.flow.service.IFlowService;
import com.dynastech.model.entity.Apply;
import com.dynastech.model.entity.ApplyExample;
import com.dynastech.model.entity.ApplyExample.Criteria;
import com.dynastech.model.entity.AttachmentFile;
import com.dynastech.model.entity.CheckDetail;
import com.dynastech.model.entity.CheckDetailExample;
import com.dynastech.model.entity.ModelEnum;
import com.dynastech.model.entity.PersonAbility;
import com.dynastech.model.entity.PersonEvaluationform;
import com.dynastech.model.entity.Plan;
import com.dynastech.model.entity.PlanExample;
import com.dynastech.model.service.IApplyService;
import com.dynastech.model.service.IPersonEvaluationFormService;
import com.dynastech.model.service.IPlanService;
import com.dynastech.system.entity.User;
import com.dynastech.system.service.IOrgManagerService;
import com.dynastech.system.service.IUserManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 我的申请
 * 
 * @author yuanhb
 *
 */
@Controller
@RequestMapping("/model/apply")
public class ApplyController {

	private static Logger logger = Logger.getLogger(ApplyController.class);

	/**
	 * 复核
	 */
	private static final String FH_CODE = "04";

	@Autowired
	private IApplyService applyService;

	@Autowired
	private CommonServiceImpl commonService;

	@Autowired
	IOrgManagerService orgManagerService;

	@Autowired
	private IDictionaryService dicService;

	@Autowired
	private IFlowService flowService;

	@Autowired
	private IPlanService planService;

	@Autowired
	private IUserManagerService userManagerService;

	@Autowired
	private IPersonEvaluationFormService pefService;

	@RequiresPermissions("apply:view")
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/apply_index");
		return mv;
	}

	@RequiresPermissions("apply:view")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size, String status) {

		PageHelper.startPage(page, size);
		PageHelper.orderBy("a.datetimecreated desc");
		Map<String, Object> data = new HashMap<String, Object>(4);

		User user = commonService.getCurrentUser();

		ApplyExample ae = new ApplyExample();
		Criteria c = ae.createCriteria();

		c.andUseridEqualTo(user.getId()).andIsdeletedEqualTo(false);

		if (!StringUtils.isBlank(status)) {
			c.andStatusEqualTo(status);
		}

		List<Apply> datas = applyService.selectByExample(ae);
		// 取分页信息
		PageInfo<Apply> pageInfo = new PageInfo<Apply>(datas);
		data.put("rows", datas);
		data.put("total", pageInfo.getTotal());

		return data;
	}

	@RequiresPermissions("apply:add")
	@RequestMapping("/add.html")
	public ModelAndView add(String orgId){
		
		ModelAndView mv = new ModelAndView();
		User user = commonService.getCurrentUser();
		mv.addObject("user", user);
		mv.addObject("sqlxList", dicService.findByKind(Constants.DIC_KIND_SQLX));
		mv.addObject("jbList", dicService.findByKind(Constants.DIC_KIND_SBJB));
		mv.addObject("xlList", dicService.findByKind(Constants.DIC_KIND_SBXL));
		mv.addObject("upi",userManagerService.findUserPostInfoByUserId(user.getId()));
		mv.setViewName("/model/apply_add");
		return mv;
	}

	@RequiresPermissions("apply:add")
	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(Apply obj, String havequalificationgradeTime2) {
		Map<String, Object> data = new HashMap<String, Object>(4);
		try {
			obj.setId(UUID.randomUUID().toString());

			Date now = new Date();
			obj.setDatetimecreated(now);
			obj.setDatetimemodified(now);

			// 复核
			if (FH_CODE.equals(obj.getType())) {
				obj.setHavequalificationgradeTime(havequalificationgradeTime2);
			}

			int a = applyService.add(obj);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success")
					: I18nUtil.getTextValue("dynastech.common.operation.fail"));

			LogUtil.addLog(logger, LogType.APPLY_ADD.getValue(), obj.getId(), commonService.getCurrentUser());

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("ApplyController.add", e);
		}
		return data;
	}

	/**
	 * 申请 部门审批时，需要选人
	 * 
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("apply:commit")
	@RequestMapping("/commit.html")
	public ModelAndView commit(String ids) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("ids", ids);

		User user = commonService.getCurrentUser();
		mv.addObject("users", userManagerService.findLeadersByOrgId(user.getId(),ModelEnum.FLOW_NODE1_CODE.getValue(),""));
		mv.addObject("ids", ids);
		mv.setViewName("/model/apply_commit");
		return mv;
	}

	/**
	 * 通过用户选择的 领导 提交申请
	 * 
	 * @param id
	 * @param leaderId
	 * @return
	 */
	@RequiresPermissions("apply:commit")
	@RequestMapping("/commit2")
	public @ResponseBody Map<String, Object> doCommit2(String id, String leaderId) {
		Map<String, Object> data = new HashMap<String, Object>(8);
		try {

			User user = commonService.getCurrentUser();

			Apply app = applyService.findById(id);
			String seq = app.getSeq();
			String year = DateUtil.getNowStr("yyyy");

			boolean ableCommit = applyService.abledCommit(user.getId(), seq, year,ModelEnum.STATUS_4.getValue());

			if (!ableCommit) {
				data.put("message", "该序列已在本年度提交过申请！");
				return data;
			}

			PlanExample pe = new PlanExample();

			String date = DateUtil.getNowStr("yyyy-MM-dd");

			pe.createCriteria().andSerialEqualTo(seq).andStarttimeLessThanOrEqualTo(date)
					.andEndtimeGreaterThanOrEqualTo(date);

			List<Plan> list = planService.selectByExample(pe);

			if (list.isEmpty()) {
				data.put("message", "未找到该序列的认证计划");
				return data;
			}

			/*List<Organization> orgs = user.getOrgs();
			List<String> orgIds = new Vector<String>();
			for (Organization org : orgs) {
				orgIds.add(org.getId());
			}*/
			// 启动流程
			List<String> userList = new ArrayList<>();
			userList.add(leaderId);
			Procinst processins = flowService.startProcess(user.getId(), Constants.FLOW_APPLY, id, userList);

			app.setProcessid(processins.getId());
			app.setSubmittime(new Date());
			app.setStatus(ModelEnum.STATUS_1.getValue());

			int a = applyService.update(app);

			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success")
					: I18nUtil.getTextValue("dynastech.common.operation.fail"));

			LogUtil.addLog(logger, LogType.APPLY_COMMIT.getValue(), id, user);

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("ApplyController.commit", e);
		}
		return data;
	}

	@RequiresPermissions("apply:delete")
	@RequestMapping("/delete")
	public @ResponseBody Map<String, Object> delete(String ids) {
		Map<String, Object> data = new HashMap<String, Object>(4);
		try {

			User user = commonService.getCurrentUser();

			ApplyExample ae = new ApplyExample();

			ae.createCriteria().andIdEqualTo(ids).andStatusEqualTo(ModelEnum.STATUS_0.getValue())
					.andUseridEqualTo(user.getId());
			Apply apply = new Apply();
			apply.setId(ids);
			apply.setIsdeleted(true);
			int a = applyService.update(ae, apply);

			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success")
					: I18nUtil.getTextValue("dynastech.common.operation.fail"));
		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("ApplyController.delete", e);
		}
		return data;
	}

	@RequiresPermissions("apply:edit")
	@RequestMapping("/edit.html")
	public ModelAndView edit(String id) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("user", commonService.getCurrentUser());
		mv.addObject("sqlxList", dicService.findByKind(Constants.DIC_KIND_SQLX));
		mv.addObject("jbList", dicService.findByKind(Constants.DIC_KIND_SBJB));
		mv.addObject("xlList", dicService.findByKind(Constants.DIC_KIND_SBXL));
		mv.addObject("apply", applyService.findById(id));

		mv.setViewName("/model/apply_edit");
		return mv;
	}

	@RequiresPermissions("apply:edit")
	@RequestMapping("/edit")
	public @ResponseBody Map<String, Object> update(Apply apply, String havequalificationgradeTime2) {
		Map<String, Object> data = new HashMap<String, Object>(4);
		try {
			/* 复核 */
			if (FH_CODE.equals(apply.getType())) {
				apply.setHavequalificationgradeTime(havequalificationgradeTime2);
			}

			int a = applyService.update(apply);
			data.put("message", a > 0 ? I18nUtil.getTextValue("dynastech.common.operation.success")
					: I18nUtil.getTextValue("dynastech.common.operation.fail"));

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("ApplyController.edit", e);
		}
		return data;
	}

	@RequestMapping("/checkDetail.html")
	public ModelAndView checkDetail(String id, String processId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.addObject("processId", processId);
		mv.setViewName("/model/apply_checkDetail");
		return mv;
	}

	/**
	 * 代码 待优化
	 */
	@RequestMapping(value = "/ExportExcel")
	public void ExportExcel(HttpServletRequest request, HttpServletResponse response, String applyId, String efId) {
		try {
			Apply apply = applyService.findById(applyId);
			String template="template01.xls";
			if(FH_CODE.equals(apply.getType())){
				template="template02.xls";
			}
			
			String path = request.getServletContext().getRealPath("") + "/WEB-INF/template/"+template;
			InputStream in = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(in);
			
			if(FH_CODE.equals(apply.getType())){
				//得到excel的第0张表 申请表
				HSSFSheet applySheet = work.getSheetAt(0);
				// 第三行
				HSSFRow applyRow = applySheet.getRow(2);
				// 姓名
				HSSFCell applycell = applyRow.getCell(1);
				applycell.setCellValue(apply.getUsername());
				// 部门
				applycell = applyRow.getCell(3);
				applycell.setCellValue(apply.getDeptname());
				// 第4行
				applyRow = applySheet.getRow(3);
				// 职种
				applycell = applyRow.getCell(1);
				applycell.setCellValue(apply.getJobstype());
				applycell = applyRow.getCell(3);
				applycell.setCellValue(apply.getJobsgrade());
				// 第5行
				applyRow = applySheet.getRow(4);
				// 申报序列
				applycell = applyRow.getCell(1);
				applycell.setCellValue(apply.getSeqV());
				// 申报级别
				applycell = applyRow.getCell(3);
				applycell.setCellValue(apply.getGradeV());
				// 第7行
				applyRow = applySheet.getRow(6);
				applycell = applyRow.getCell(1);
				applycell.setCellValue(apply.getHavequalificationseq());
				applycell = applyRow.getCell(3);
				applycell.setCellValue(apply.getHavequalificationgrade());
				// 第8行
				applyRow = applySheet.getRow(7);
				applycell = applyRow.getCell(1);
				applycell.setCellValue(apply.getHavequalificationgradeTime());
				// 第9行
				applyRow = applySheet.getRow(8);
				applycell = applyRow.getCell(0);
				String sfyx="是";
				if("0".equals(apply.getNuclearresultsfront())){
					sfyx="否";
					applycell.setCellValue("取得资格时间后年度考核结果是否有优秀或排序靠前 ："+sfyx);
				}else{
					String year=apply.getNuclearresultsfrontAnnual();
					applycell.setCellValue("取得资格时间后年度考核结果是否有优秀或排序靠前 ："+sfyx+"，年度为 "+year);
				}
				//取得资格时间后年度考核结果是否有优秀或排序靠前 ：□ 是，年度为____________  □ 否
				// 第10行
				applyRow = applySheet.getRow(9);
				applycell = applyRow.getCell(0);
				apply.getImprovementplanclosedloop();
				applycell.setCellValue("改进计划是否闭环 ："+(apply.getImprovementplanclosedloop()==true?"是":"否"));
				// 第12行
				CheckDetailExample cde = new CheckDetailExample();
				cde.createCriteria().andApplyidEqualTo(applyId).andNodecodeEqualTo(ModelEnum.FLOW_NODE1_CODE.getValue());
				cde.setOrderByClause(" createdTime desc");
				List<CheckDetail> cdList = applyService.selectByExample(cde);
				
				String bbmld = "";
				String bbmldUser ="";
				String bbmldTime="";
				
				if(cdList.size()>0){
					CheckDetail cd =cdList.get(0);
					bbmld = cdList.size()<1 ? "" : cd.getOpinion();
					bbmldUser =cd.getUsername();
					bbmldTime=DateUtil.dateToStr((DateUtil.parseStrToDate(cd.getCreatedtime(), DateUtil.YYYY_MM_DD)),DateUtil.YYYY_MM_DD);
				}
				
				cde.clear();
				cde.createCriteria().andApplyidEqualTo(applyId).andNodecodeEqualTo(ModelEnum.FLOW_NODE2_CODE.getValue());
				cde.setOrderByClause(" createdTime desc");
				cdList = applyService.selectByExample(cde);
				
				String syb = "";
				String sybUser ="";
				String sybTime="";
				if(cdList.size()>1){
					CheckDetail cd=cdList.get(0);
					syb = cdList.size()<1 ? "" : cd.getOpinion();
					sybUser =cd.getUsername();
					sybTime=DateUtil.dateToStr((DateUtil.parseStrToDate( cd.getCreatedtime(),DateUtil.YYYY_MM_DD)),DateUtil.YYYY_MM_DD);
				}
				

				applyRow = applySheet.getRow(11);
				applycell = applyRow.getCell(0);
				applycell.setCellValue(bbmld);
				applycell = applyRow.getCell(2);
				applycell.setCellValue(syb);
				//第13行
				applyRow = applySheet.getRow(12);
				applycell = applyRow.getCell(1);
				applycell.setCellValue(bbmldUser);
				applycell = applyRow.getCell(3);
				applycell.setCellValue(sybUser);
				//第14行
				applyRow = applySheet.getRow(13);
				applycell = applyRow.getCell(1);
				applycell.setCellValue(bbmldTime);
				applycell = applyRow.getCell(3);
				applycell.setCellValue(sybTime);
			}else{
				//得到excel的第0张表 申请表
				HSSFSheet applySheet = work.getSheetAt(0);
				// 第三行
				HSSFRow applyRow = applySheet.getRow(2);
				// 姓名
				HSSFCell applycell = applyRow.getCell(1);
				applycell.setCellValue(apply.getUsername());
				// 部门
				applycell = applyRow.getCell(3);
				applycell.setCellValue(apply.getDeptname());
				// 第4行
				applyRow = applySheet.getRow(3);
				// 申报类型
				applycell = applyRow.getCell(1);
				applycell.setCellValue(apply.getTypeV());
				// 第5行
				applyRow = applySheet.getRow(4);
				// 申报序列
				applycell = applyRow.getCell(1);
				applycell.setCellValue(apply.getSeqV());
				// 申报级别
				applycell = applyRow.getCell(3);
				applycell.setCellValue(apply.getGradeV());
				// 第7行
				applyRow = applySheet.getRow(6);
				// 职种
				applycell = applyRow.getCell(1);
				applycell.setCellValue(apply.getJobstypeV());
				// 级别
				applycell = applyRow.getCell(3);
				String jv=apply.getJobsgradeV();
				applycell.setCellValue(jv);
				// 第8行
				applyRow = applySheet.getRow(7);
				applycell = applyRow.getCell(1);
				applycell.setCellValue(apply.getHavequalificationseqV());
				applycell = applyRow.getCell(3);
				applycell.setCellValue(apply.getHavequalificationgradeV() + "/" + apply.getHavequalificationgradeTime());
				// 第9行
				applyRow = applySheet.getRow(8);
				applycell = applyRow.getCell(1);
				applycell.setCellValue(apply.getWorkconditionsseqV());
				applycell = applyRow.getCell(3);
				applycell.setCellValue(apply.getWorkconditionsgradeV() + "/" + apply.getWorkconditionsgradeAnnual());
				// 第11行
				applyRow = applySheet.getRow(10);
				applycell = applyRow.getCell(1);
				applycell.setCellValue(apply.getGraduateschool());
				applycell = applyRow.getCell(3);
				applycell.setCellValue(apply.getMajors());
				// 第12行
				applyRow = applySheet.getRow(11);
				applycell = applyRow.getCell(1);
				applycell.setCellValue(apply.getEducational());
				applycell = applyRow.getCell(3);
				applycell.setCellValue(apply.getDegree());
				// 第14行
				applyRow = applySheet.getRow(13);
				applycell = applyRow.getCell(0);
				applycell.setCellValue(apply.getExperience());
				// 第15行
				applyRow = applySheet.getRow(14);
				applycell = applyRow.getCell(0);
				applycell.setCellValue(apply.getStandardsmatching());
				// 第17行
				CheckDetailExample cde = new CheckDetailExample();
				cde.createCriteria().andApplyidEqualTo(applyId).andNodecodeEqualTo(ModelEnum.FLOW_NODE1_CODE.getValue());
				cde.setOrderByClause(" createdTime desc");
				List<CheckDetail> cdList = applyService.selectByExample(cde);
				String bbmld = cdList.size()<1 ? "" : cdList.get(0).getOpinion();
				String bbmldUser =cdList.get(0).getUsername();
				String bbmldTime=DateUtil.dateToStr((DateUtil.parseStrToDate( cdList.get(0).getCreatedtime(), DateUtil.YYYY_MM_DD)),DateUtil.YYYY_MM_DD);
				cde.clear();
				cde.createCriteria().andApplyidEqualTo(applyId).andNodecodeEqualTo(ModelEnum.FLOW_NODE2_CODE.getValue());
				cde.setOrderByClause(" createdTime desc");
				cdList = applyService.selectByExample(cde);
				String syb = "";
				String sybUser ="";
				String sybTime="";
				if(!cdList.isEmpty()){
					syb=cdList.get(0).getOpinion();
					sybUser=cdList.get(0).getUsername();
					sybTime=DateUtil.dateToStr((DateUtil.parseStrToDate( cdList.get(0).getCreatedtime(), DateUtil.YYYY_MM_DD)),DateUtil.YYYY_MM_DD);
				}
				applyRow = applySheet.getRow(16);
				applycell = applyRow.getCell(0);
				applycell.setCellValue(bbmld);
				applycell = applyRow.getCell(2);
				applycell.setCellValue(syb);
				//第18行
				applyRow = applySheet.getRow(17);
				applycell = applyRow.getCell(1);
				applycell.setCellValue(bbmldUser);
				applycell = applyRow.getCell(3);
				applycell.setCellValue(sybUser);
				//第19行
				applyRow = applySheet.getRow(18);
				applycell = applyRow.getCell(1);
				applycell.setCellValue(bbmldTime);
				applycell = applyRow.getCell(3);
				applycell.setCellValue(sybTime);
			}
			/*------------- begin 测评表 ---------------------------*/

			if (!StringUtils.isBlank(efId)) {
				// 得到excel的第1张表 测评表
				HSSFSheet efSheet = work.getSheetAt(1);
				// 得到第1行的第一个单元格的样式
				HSSFCellStyle rowCellStyle = efSheet.getRow(0).getCell(0).getCellStyle();
				// 内容的样式
				HSSFCellStyle rowCellStyle2 = efSheet.getRow(2).getCell(0).getCellStyle();
				// 这里面的行从0开始
				HSSFRow row = efSheet.getRow(0);
				HSSFCell cell = row.getCell(0);
				PersonEvaluationform pef = pefService.findById(efId);

				cell.setCellValue(pef.getCnSerial() + "（" + pef.getCnLevel() + ")");
				rowCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cell.setCellStyle(rowCellStyle);
				// 计数器
				int i = 2;
				List<PersonAbility> list = pefService.findPersonAbilityByEvalId(efId);

				int begin = i;
				int end = i;
				String typeId = "";

				for (PersonAbility pa : list) {
					row = efSheet.createRow(i);
					cell = row.createCell(0);
					cell.setCellValue(pa.getTypeV());
					cell.setCellStyle(rowCellStyle2);
					cell = row.createCell(1);
					cell.setCellValue(pa.getName());
					cell.setCellStyle(rowCellStyle2);
					cell = row.createCell(2);
					cell.setCellValue(pa.getEvidence());
					cell.setCellStyle(rowCellStyle2);
					cell = row.createCell(3);

					List<AttachmentFile> files = pa.getFiles();
					StringBuffer fileSB = new StringBuffer();
					for (AttachmentFile af : files) {
						fileSB.append(af.getFriendlyfilename());
						fileSB.append(":");
						fileSB.append(af.getDescription());
					}
					cell.setCellValue(fileSB.toString());
					cell.setCellStyle(rowCellStyle2);
					cell = row.createCell(4);
					cell.setCellValue(pa.getScore());
					cell.setCellStyle(rowCellStyle2);
					cell = row.createCell(5);
					cell.setCellValue(pefService.count(pa.getId(), efId));
					cell.setCellStyle(rowCellStyle2);
					i++;
					// 类别相同就记录 该行
					if (typeId.equals(pa.getTypeid())) {
						end++;
					} else {
						// 类别不同了，就应该合并上方相同
						if (!StringUtils.isBlank(typeId)) {
							efSheet.addMergedRegion(new CellRangeAddress(begin, end, 0, 0));
							end++;
							begin = end;
						}
					}
					typeId = pa.getTypeid();
				}
				// 合同表格最后该合并且没有合并的行
				if (begin != end) {
					efSheet.addMergedRegion(new CellRangeAddress(begin, end, 0, 0));
				}

				int nn = efSheet.getPhysicalNumberOfRows();

				for (int num = 0; num < nn; num++) {
					ExportExcelUtil.calcAndSetRowHeigt(efSheet.getRow(num));
				}
			}

			String name = apply.getUsername();
			try {
				name = new String(name.getBytes("gb2312"), "ISO8859-1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

			OutputStream os = response.getOutputStream();// 取得输出流
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + name + ".xls");
			work.write(os);
			os.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
