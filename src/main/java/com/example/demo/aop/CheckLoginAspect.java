package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Aspect
@Component
public class CheckLoginAspect {

	@Autowired
	User user;
	// 未ログインの場合ログインページにリダイレクト
	@Around("execution(* com.example.demo.controller.PlanController.*(..))")
	public Object checkLogin(ProceedingJoinPoint jp) throws Throwable {

		if (user == null || user.getName() == null
				|| user.getName().length() == 0) {
			System.err.println("ログインしていません!");
			// リダイレクト先を指定する
			return "redirect:/login?error=notLoggedIn";
		}
		// Controller内のメソッドの実行
		return jp.proceed();
	}
}
