package com.uxsino.AgentConsole.controllerBackup;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统管理员controller
 * @author admin
 * 包括：
 *  1.创建并管理其他账号
 *  2.用户账号审批
 *  3.暂停、启动系统服务
 *  （凡是查询均需带分页）
 */

@RestController
@RequestMapping(value = "/systemAdmin")
public class SystemAdminController {

	//创建管理员账号
	@PostMapping(value = "/roleAdd")
	public Map<String,String> roleAdd(@RequestParam("accountname") String accountname,
									  @RequestParam("accountRole") String accountRole,
									  @RequestParam("way") String way,
									  @RequestParam("status") String status){
		Map map = new HashMap();
		/*
		将账号名和角色以及SQL发送给服务端，如何后台返回成功，则：
		 */

		//将消息通过协议方式发送给服务端

		//接收服务端返回值
		String str = "";
		//这个If是判断返回是否OK 如果OK则进行给前端返回正确的码

		if ("string".equals(str)){
			map.put("Data","添加成功");
			map.put("Code","201");
		}
		return map;
	}

	//解锁、锁定各管理员账号
	@PutMapping(value = "/updateRoleStatus")
	public Map<String,String> updateStatus(@RequestParam("status") String status,
										   @RequestParam("accountname") String accountname){
		Map map = new HashMap();
		map.put("Data","更改成功");
		map.put("Code","201");
		return map;
	}

	//删除管理员账号
	@DeleteMapping(value = "/deleteRole")
	public Map<String,String> deleteRole(@RequestParam("name") String name){
		Map<String,String> map = new HashMap();
		/*
		加入通信消息
		 */
		map.put("Data","删除成功");
		map.put("Code","204");
		return map;
	}

	//查询所有管理员账号信息
	@GetMapping(value = "/listRole")
	public Map<String,String> listRole(@RequestParam("page") String page){
		Map<String,String> map = new HashMap();
		return map;
	}

	//审批用户状态和解锁、锁定用户状态接口
	@PutMapping(value = "/updateUserStatus")
	public Map<String,String> updateUserStatus(@RequestParam("status") String status,
											   @RequestParam("userName") String userName){
		Map<String,String> map = new HashMap();
		return map;
	}

	//删除用户账号
	@DeleteMapping(value = "/deleteuser")
	public Map<String,String> deleteuser(@RequestParam("userName") String userName){
		Map<String,String> map = new HashMap();
		return map;
	}

	//查询所有用户账号信息
	@GetMapping(value = "/listUser")
	public Map<String,String> listUser(@RequestParam("page") String page,
									   @RequestParam("status") String status){
		Map<String,String> map = new HashMap();
		return map;
	}

	//根据用户名查询用户
	@GetMapping(value = "/getByUserName")
	public Map<String,String> getByUserName(@RequestParam("userName") String userName){
		Map<String,String> map = new HashMap();
		return map;
	}

	//根据审批状态查询用户
	@GetMapping(value = "/getByStatus")
	public Map<String,String> getByStatus(@RequestParam("status") String status){
		Map<String,String> map = new HashMap();
		return map;
	}

	//修改用户控件配额信息
	@PutMapping(value = "/updatUserSpace")
	public Map<String,String> updatUserSpace(@RequestParam("userName") String userName,
											 @RequestParam("applicationSpace") String applicationSpace){
		Map<String,String> map = new HashMap();
		return map;
	}

}
