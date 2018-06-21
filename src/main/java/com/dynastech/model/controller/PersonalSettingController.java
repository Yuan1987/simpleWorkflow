package com.dynastech.model.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ganlu on 2017/9/15.
 */

@Controller
@RequestMapping("/system/personal-settings")
public class PersonalSettingController {

/*
    private static Logger logger = Logger.getLogger(PersonalSettingController.class);


    @Autowired
    private ITAbilityService itAbilityService;


    //@RequiresPermissions("dic:view")
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/model/myAbility");
        return mv;
    }


   // @RequiresPermissions("dic:view")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> list(HttpServletRequest request,
                             @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                             @RequestParam(value = "size", defaultValue = "20", required = false) int size,
                             @RequestParam(value = "searchText", required = false) String searchText) {

        PageHelper.startPage(page, size);

        Map<String, Object> data = new HashMap<String, Object>();



        List<TAbility> tAbilityList = itAbilityService.getAbilityByUserId("00");
        // 取分页信息
        PageInfo<TAbility> pageInfo = new PageInfo<TAbility>(tAbilityList);
        data.put("rows", tAbilityList);
        data.put("total", pageInfo.getTotal());

        return data;
    }*/

}
