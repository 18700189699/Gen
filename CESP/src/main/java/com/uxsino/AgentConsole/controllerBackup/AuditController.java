package com.uxsino.AgentConsole.controllerBackup;

import com.uxsino.AgentConsole.bean.AdminLog;
import com.uxsino.AgentConsole.bean.SystemLog;
import com.uxsino.AgentConsole.bean.UserLog;
import com.uxsino.AgentConsole.service.AuditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统审计员
 * 查询日志相关操作
 * @author admin
 * 包括：
 *  1.用户管理
 *  2.审计日志
 *  3.日志查询
 */
@RestController
@RequestMapping(value = "/audit")
public class AuditController {

	//查询所有用户列表
	@GetMapping(value = "/listUser")
	public Map<String,String> listUser(@RequestParam("page") String page){
		Map<String,String> map = new HashMap<>();

		//将查询的对象通过通信的方式返回
		return map;
	}

	//分组查询用户
	@GetMapping(value = "/listDeptUser")
	public Map<String,String> listDeptUser(@RequestParam("page") String page){
		Map<String,String> map = new HashMap<>();

		//将查询的对象通过通信的方式返回
		return map;
	}

	//查询所有用户日志信息
	@GetMapping(value="/queryUserLog")
	public Map queryUserLog(HttpServletRequest request){
		Map map = new HashMap();
		//request.getSession().setAttribute("listUserLog",list);
		return map;
	}

	//查询所有系统日志信息
	@GetMapping(value="/querySystemLog")
	public Map querySystemLog(HttpServletRequest request){
		Map map = new HashMap();
		return map;
	}

	//查询所有管理员日志信息
	@GetMapping(value="/queryAdminLog")
	public Map queryAdminLog(){
		Map map = new HashMap();
		return map;
	}

	//根据条件查询用户日志信息
	@GetMapping(value="/queryUserLogAnd")
	public Map<String,String> listUser(@RequestParam("page") String page,
									   @RequestParam("startTime") String startTime,
									   @RequestParam("endTime") String endTime,
									   @RequestParam("computerIP") String computerIP,
									   @RequestParam("userName") String userName,
									   @RequestParam("type") String type){
		Map<String,String> map = new HashMap();
		return map;
	}

	//根据条件查询管理员日志信息
	@GetMapping(value="/queryAdminLogAnd")
	public Map<String,String> queryAdminLogAnd(@RequestParam("page") String page,
											   @RequestParam("startTime") String startTime,
											   @RequestParam("endTime") String endTime,
											   @RequestParam("computerIP") String computerIP,
											   @RequestParam("userName") String userName,
											   @RequestParam("type") String type){
		Map<String,String> map = new HashMap();
		return map;
	}

	//根据条件查询系统日志信息
	@GetMapping(value="/querySystemLogAnd")
	public Map<String,String> querySystemLogAnd(@RequestParam("page") String page,
												@RequestParam("startTime") String startTime,
												@RequestParam("endTime") String endTime,
												@RequestParam("computerIP") String computerIP,
												@RequestParam("userName") String userName,
												@RequestParam("type") String type){
		Map<String,String> map = new HashMap();
		return map;
	}

}
