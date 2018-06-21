package com.dynastech.model.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dynastech.base.common.service.impl.CommonServiceImpl;
import com.dynastech.base.log.LogType;
import com.dynastech.base.util.BeanUtils;
import com.dynastech.base.util.I18nUtil;
import com.dynastech.base.util.LogUtil;
import com.dynastech.common.service.impl.DictionaryService;
import com.dynastech.model.entity.SysAbility;
import com.dynastech.model.entity.SysAbilityExample;
import com.dynastech.model.entity.SysAbilityRequest;
import com.dynastech.model.entity.SysEvaluationForm;
import com.dynastech.model.entity.SysEvaluationFormExample;
import com.dynastech.model.entity.SysEvaluationRequest;
import com.dynastech.model.service.ISysAbilityService;
import com.dynastech.model.service.ISysEvaluationformService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 功能：评测表管理
 * @author ganlu
 * 
 * yuanhb 修改
 * 2017年10月31日 ：1、修改bug：编辑测评表时，重复添加能力项问题
 * 				2、增加excel 在线预览功能
 * 另外：发现该功能的原创代码臃肿、而且未考虑性能。 以后建议优化	
 */
@Controller
@RequestMapping("/system/base-evaluation")
public class SysEvaluationController {
	
	private static Logger logger = Logger.getLogger(SysEvaluationController.class);

	@Autowired
	private ISysEvaluationformService sysEvaluationformService;

	@Autowired
	private CommonServiceImpl commonService;

	@Autowired
	private DictionaryService dicService;

	@Autowired
	private ISysAbilityService sysAbilityService;

	/**
	 * 评测表首页
	 * 
	 * @return
	 */
	// @RequiresPermissions("base-evalu:view")
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/model/sys_evaluation");
		return mv;
	}

	/**
	 * 评测表列表
	 * 
	 * @param request
	 *            request对象
	 * @param page
	 *            当前页
	 * @param size
	 *            页大小
	 * @param searchText
	 *            搜索文本
	 * @return
	 */
	// @RequiresPermissions("base-evalu:view")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size,
			@RequestParam(value = "searchText", required = false) String searchText) {

		PageHelper.startPage(page, size);
		Map<String, Object> data = new HashMap<>(4);
		SysEvaluationFormExample cond = new SysEvaluationFormExample();
		SysEvaluationFormExample.Criteria c = cond.createCriteria();
		if (!StringUtils.isBlank(searchText)) {
			c.andNameLike("%" + searchText + "%");
		}
		c.andIsdeletedEqualTo(false);
		cond.setOrderByClause("name");
		List<SysEvaluationForm> list = sysEvaluationformService.getSysEvaluationFormList(cond);
		// 取分页信息
		PageInfo<SysEvaluationForm> pageInfo = new PageInfo<SysEvaluationForm>(list);
		data.put("rows", list);
		data.put("total", pageInfo.getTotal());
		return data;
	}

	/**
	 * 获取能力类别
	 * 
	 * @param serial
	 *            序列
	 * @param theLevel
	 *            等级
	 * @return
	 */
	@RequestMapping(value = "/getabilities", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getabilities(String serial,String theLevel) {

		Map<String, Object> map = new HashMap<>(4);
		SysAbilityExample cond = new SysAbilityExample();
		SysAbilityExample.Criteria c = cond.createCriteria();
		c.andSerialEqualTo(serial).andIsdeletedEqualTo(false).andThelevelEqualTo(theLevel);
		cond.setOrderByClause("name");
		List<SysAbility> list = sysAbilityService.getSysAbilityList(cond);
		map.put("rows", list);
		return map;
	}

	/**
	 * 新增测评表
	 * 
	 * @return
	 */
	// @RequiresPermissions("base-evalu:add")
	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView();
		List<com.dynastech.common.entity.Dictionary> dics = dicService.findByKind("sbxl");
		mv.addObject("dics_sbxl", dics);
		List<com.dynastech.common.entity.Dictionary> dics2 = dicService.findByKind("sbjb");
		mv.addObject("dics_ssjb", dics2);
		List<com.dynastech.common.entity.Dictionary> dics3 = dicService.findByKind("formula");
		mv.addObject("dics_formula", dics3);
		List<com.dynastech.common.entity.Dictionary> dics4 = dicService.findByKind("abilitykind");
		mv.addObject("dics_abilitykind", dics4);

		mv.setViewName("/model/sys_evaluation_add");
		return mv;
	}

	/**
	 * 保存评测表
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	// @RequiresPermissions("base-evalu:add")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> add(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String strEvaluationInfo = request.getParameter("hidEvaluationInfo");
		String abilityInfo = request.getParameter("hidabilityInfo");
		String operation = request.getParameter("operationtype");
		Map<String, Object> data = new HashMap<>(8);
		try {
			SysEvaluationRequest sysEvaluationRequest = JSON.parseObject(strEvaluationInfo, SysEvaluationRequest.class);
			List<SysAbilityRequest> sysAbilityRequest = JSONArray.parseArray(abilityInfo, SysAbilityRequest.class);
			// 判断同一测评表能力项是否有重复
			if (new HashSet<>(sysAbilityRequest).size() != sysAbilityRequest.size()) {
				data.put("message", "能力项存在重复！");
				return data;
			}
			SysEvaluationForm sysEvaluationForm = new SysEvaluationForm();
			List<SysAbility> sysAbilitiesList = new ArrayList<>();
			BeanUtils.copy(sysEvaluationForm, sysEvaluationRequest);
			sysAbilityRequest.forEach(s -> {
				SysAbility sab = new SysAbility();
				try {
					BeanUtils.copy(sab, s);
					sysAbilitiesList.add(sab);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			BeanUtils.copy(sysAbilitiesList, sysAbilityRequest);

			if ("add".equals(operation)) {
				sysEvaluationformService.addSysEvaluationFormAndAbility(file, sysEvaluationForm, sysAbilitiesList);
			} else {
				sysEvaluationformService.editSysEvaluationFormAndAbility(file, sysEvaluationForm, sysAbilitiesList);
			}
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.success"));
			LogUtil.addLog(logger, LogType.EVALU_ADD.getValue(), sysEvaluationForm.getId(),
					commonService.getCurrentUser());

		} catch (Exception e) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.add", e);
		}
		return data;
	}

	/**
	 * 编辑测评表
	 * 
	 * @param id
	 *            单号
	 * @return
	 */
	@RequestMapping("/edit.html")
	public ModelAndView edit(String id) {
		ModelAndView mv = new ModelAndView();
		List<com.dynastech.common.entity.Dictionary> dics = dicService.findByKind("sbxl");
		mv.addObject("dics_sbxl", dics);
		List<com.dynastech.common.entity.Dictionary> dics2 = dicService.findByKind("sbjb");
		mv.addObject("dics_ssjb", dics2);
		List<com.dynastech.common.entity.Dictionary> dics3 = dicService.findByKind("formula");
		mv.addObject("dics_formula", dics3);
		SysEvaluationForm sysEvaluationForm = sysEvaluationformService.getSysEvaluationForm(id);

		List<com.dynastech.common.entity.Dictionary> dics4 = dicService.findByKind("abilitykind");
		mv.addObject("dics_abilitykind", dics4);

		mv.addObject("evalu_Info", sysEvaluationForm);
		List<SysAbility> abilities = sysEvaluationformService.getSysAbilities(id);
		mv.addObject("abilites", abilities);
		mv.addObject("fileInfo", sysEvaluationformService.getAttachmentFile(sysEvaluationForm.getStandardfile()));
		mv.setViewName("/model/sys_evaluation_edit");
		return mv;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Map<String, Object> delete(String id) {
		Map<String, Object> data = new HashMap<>(4);
		try {
			sysEvaluationformService.deleteSysEvaluationFormAndAbility(id);
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.success"));
			LogUtil.addLog(logger, LogType.EVALU_DELETE.getValue(), id, commonService.getCurrentUser());
		} catch (Exception ex) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.add", ex);
		}
		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/status", method = RequestMethod.POST)
	public Map<String, Object> editStatus(SysEvaluationForm sysEvaluationForm) {
		Map<String, Object> data = new HashMap<String, Object>(4);
		try {

			sysEvaluationformService.updateStatus(sysEvaluationForm);

			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.success"));
		} catch (Exception ex) {
			data.put("message", I18nUtil.getTextValue("dynastech.common.operation.fail"));
			logger.error("OrgManagerController.add", ex);
		}
		return data;
	}

	@RequestMapping(value="/excelView")
	@ResponseBody
	public String excelView(HttpServletRequest request,HttpServletResponse response,String id) {

		try {
			String path = request.getServletContext().getRealPath("") + "/WEB-INF/template/sysef.xls";
			InputStream in = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(in);
			// 得到excel的第0张表
			HSSFSheet sheet = work.getSheetAt(0);
			// 得到第1行的第一个单元格的样式
			HSSFCellStyle rowCellStyle = sheet.getRow(0).getCell(0).getCellStyle();
			//内容的样式
			HSSFCellStyle rowCellStyle2 = sheet.getRow(2).getCell(0).getCellStyle();
			// 这里面的行和列的数法与计算机里的一样,从0开始是第一
			HSSFRow row = sheet.getRow(0);
			HSSFCell cell = row.getCell(0);
			SysEvaluationForm sef= sysEvaluationformService.getSysEvaluationForm(id);
			
			cell.setCellValue(sef.getCnSerial()+"（"+sef.getCnLevel()+")");
			rowCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cell.setCellStyle(rowCellStyle);
			// 计数器
			int i = 2;
			List<SysAbility> list = sysEvaluationformService.getSysAbilities(id);
			
			int begin=i;
			int end=i;
			String typeId="";
			for (SysAbility sa : list){
				row = sheet.createRow(i);
				cell = row.createCell(0);
				cell.setCellValue(sa.getTypeV());
				cell.setCellStyle(rowCellStyle2);
				cell = row.createCell(1);
				cell.setCellValue(sa.getName());
				cell.setCellStyle(rowCellStyle2);
				cell = row.createCell(2);
				cell.setCellValue(sa.getEvidence());
				cell.setCellStyle(rowCellStyle2);
				cell = row.createCell(3);
				cell.setCellValue("");
				cell.setCellStyle(rowCellStyle2);
				cell = row.createCell(4);
				cell.setCellValue(sa.getScore());
				cell.setCellStyle(rowCellStyle2);
				cell = row.createCell(5);
				cell.setCellValue("");
				cell.setCellStyle(rowCellStyle2);
				i++;
				//类别相同就记录 该行
				if(typeId.equals(sa.getTypeid())){
					end++;
				}else{
					//类别不同了，就应该合并上方相同
					if(!StringUtils.isBlank(typeId)){
						sheet.addMergedRegion(new CellRangeAddress(begin,end,0,0));
						end++;
						begin=end;
					}
				}
				typeId=sa.getTypeid();
			}
			//合同表格最后该合并且没有合并的行
			if(begin!=end){
				sheet.addMergedRegion(new CellRangeAddress(begin,end,0,0));
			}
			
			StringWriter  writer=null;
		    try {
			   	work.setSheetName(0," ");
	            ExcelToHtmlConverter converter = new ExcelToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
	            // 不显示列的表头
	            converter.setOutputColumnHeaders(false);
	            // 不显示行的表头
	            converter.setOutputRowNumbers(false);
	            converter.processWorkbook(work);

	            writer = new StringWriter();
	            Transformer serializer;
				serializer = TransformerFactory.newInstance().newTransformer();
	            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	            serializer.setOutputProperty(OutputKeys.METHOD, "html");
	            serializer.transform(new DOMSource(converter.getDocument()),new StreamResult(writer));
	            String content = writer.toString();
	            response.getWriter().write(content);
	            writer.close();
	        } finally {
	            try {
	                if (writer != null) {
	                    writer.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

		} catch (FileNotFoundException e) {
			System.out.println("文件路径错误");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件输入流错误");
			e.printStackTrace();
		}catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}catch (TransformerException e) {
			e.printStackTrace();
		}catch (ParserConfigurationException | FactoryConfigurationError e) {
			e.printStackTrace();
		}
		return null;
	}
}
