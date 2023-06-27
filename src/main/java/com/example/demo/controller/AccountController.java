package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.model.User;
import com.example.demo.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {
	@Autowired
	HttpSession session;
	
	@Autowired
	User users;

	@Autowired
	AccountRepository accountRepository;

	// ログイン画面を表示
	@GetMapping("/login")
	public String index() {
		// セッション情報を全てクリアする
		session.invalidate();

		return "login";
	}

	// ログインを実行
	@PostMapping("/login")
	public String login(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model m) {
		
		List<String> errorLog = new ArrayList<>();
		
		if (name == null || name.length() == 0) {
			errorLog.add("名前を入力してください");
		}
		if (password == null || password.length() == 0) {
			errorLog.add("パスワードを入力してください");
		}
		
		List<Account> list = null;
		
		if (name.length() != 0 && password.length() != 0) {
			list = accountRepository.findByNameAndPassword(name, password);
			if (list.size() <= 0){
				errorLog.add("名前とパスワードが一致しません");
			}
		}
		
		if (errorLog.size() > 0) {
			m.addAttribute("errorLog", errorLog);
			return "login";
		}
		
		Account account = list.get(0);
		
		users.setName(account.getName());
		
		return "form";
	}

	//新規登録
	@GetMapping("/account")
	public String create(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "password", defaultValue = "") String password,
			Model model) {
	
		model.addAttribute("name" , name);
		model.addAttribute("password" , password);
		
		return "addAccount";
	}
	
	//新規登録処理
	@PostMapping("/account/add")
	public String store(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "password", defaultValue = "") String password,
			Model model) {

		List<String> error = new ArrayList<>();

		if (name.equals("") == true) {
			error.add("名前は必須です!");
			//	return "contactForm";
		}

		if (password.equals("") == true) {
			error.add("パスワードは必須です!");
			//	return "contactForm";
		}
		
		List<Account> list = accountRepository.findByName(name);
		if (list.size() > 0) {
		error.add("既に同じ名前のアカウントが存在しています!");
		}
		
		if (error.size() > 0) {
			model.addAttribute("error", error);
			
			Account account = new Account(name, password);
			model.addAttribute("account", account);
			
			return "addAccount";
		}

		Account account = new Account(name, password);
		model.addAttribute("account", account);
		accountRepository.save(account);

		return "accountConfirm";
	}

	//ログイン画面へ遷移
	
	@PostMapping("/account/finish")
	public String finish() {
				return "login";
	}
}
