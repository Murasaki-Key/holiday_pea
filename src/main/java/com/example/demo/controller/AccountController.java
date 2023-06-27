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
		List<Account> user = accountRepository.findLikeByName(name);
		if (user.isEmpty() == true) {
			String error = "名前とパスワードが一致しませんでした";
			m.addAttribute("error", error);
			return "login";
		}

		String pas = user.get(0).getPassword();
		if (pas.equals(password) == false) {
			String error = "名前とパスワードが一致しませんでした";
			m.addAttribute("error", error);
			return "login";
		}
		String names = user.get(0).getName();
		users.setName(names);
		
		String name1 = users.getName();
		m.addAttribute("names",name1);

		// リダイレクト
		return "/form";
	}

	//新規登録
	@GetMapping("/account")
	public String create(Model model) {
		Account account = new Account();

		model.addAttribute("account", account);
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
			error.add("名前は必須です");
			//	return "contactForm";
		}

		if (password.equals("") == true) {
			error.add("パスワードは必須です");
			//	return "contactForm";
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

}
