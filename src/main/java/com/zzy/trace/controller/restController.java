package com.zzy.trace.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzy.trace.bean.axiosBean;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
@Controller
public class restController {
	public static List<axiosBean> abpool= new ArrayList<>();
	
    /*restful 部分*/
    @GetMapping("/abpool")
    @ResponseBody
    public JSONArray list(@RequestParam(value = "start", defaultValue = "1") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
    	JSONArray lab = JSONUtil.parseArray(abpool);
        return lab;
    }
     
    @GetMapping("/abpool/{id}")
    @ResponseBody
    public JSONObject get(@PathVariable("id") int id) throws Exception {
        if(abpool.size() == 0 || abpool.size() < id) {
        	return JSONUtil.createObj();
        }else {
        	axiosBean ab = abpool.get(id);
	        return JSONUtil.parseObj(ab);
        }
    }
     
    @PostMapping("/abpool")
    @ResponseBody
    public String add(@RequestBody axiosBean ab) throws Exception {
    	abpool.add(ab);
        return "success";
    }
    
    @DeleteMapping("/abpool/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") int id) throws Exception {
        if(abpool.size() == 0 ) {
        	;
        }else {
        	for(axiosBean ab : abpool) {
        		if(ab.id == id) {
        			abpool.remove(ab);
        			break;
        		}
        	}
        }
        return "success";
    }
    
    @DeleteMapping("/abpool")
    @ResponseBody
    public String delete() throws Exception {
    	abpool.clear();
        return "success";
    }
    
    @PutMapping("/abpool")
    @ResponseBody
    public String update(@RequestBody axiosBean ab) throws Exception {
        if(abpool.size() == 0 ) {
        	;
        }else {
        	for(int i = 0 ; i < abpool.size() ; i++) {
        		if(abpool.get(i).id == ab.id) {
        			abpool.set(i,ab);
        			break;
        		}
        	}
        }
        return "success";
    }
    
    @GetMapping("/viewab")
    @ResponseBody
    public JSONArray view() throws Exception {
        return JSONUtil.parseArray(abpool); 
    }
}
