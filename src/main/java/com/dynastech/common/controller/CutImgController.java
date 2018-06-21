package com.dynastech.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.dynastech.base.util.ImageCut;

@Controller
@RequestMapping("/common")
public class CutImgController {

	@RequestMapping(value = "/cutImg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cutImage(MultipartFile file, String avatar_data) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();

		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) JSON.parseObject(avatar_data, Map.class);

		Double x = Double.valueOf(map.get("x") + "");
		Double y = Double.valueOf(map.get("y") + "");
		Double h = Double.valueOf(map.get("height") + "");
		Double w = Double.valueOf(map.get("width") + "");
		Double rotate = Double.valueOf(map.get("rotate") + "");

		String base64 = ImageCut.cutByteImage(file.getBytes(), x.intValue(), y.intValue(), w.intValue(), h.intValue(),
				rotate.intValue());

		data.put("result", base64);

		return data;
	}


}
