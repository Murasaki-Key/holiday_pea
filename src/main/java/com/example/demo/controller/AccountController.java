package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {
	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	AccountRepository accountRepository;

	// ログイン画面を表示
	@GetMapping({ "/login", "/logout" })
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
			String message = "名前とパスワードが一致しませんでした";
			m.addAttribute("message", message);
			return "login";
		}

		String pas = user.get(0).getPassword();
		if (pas.equals(password) == false) {
			String message = "名前とパスワードが一致しませんでした";
			m.addAttribute("message", message);
			return "login";
		}
		String names = user.get(0).getName();
		account.setName(names);

		// リダイレクト
		return "redirect:/users";
	}

	// 新規登録処理
	@PostMapping("/accoont/add")
	public String store(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "password", defaultValue = "") String password,
			Model model) {

		// Userオブジェクトの生成
		Account user = new Account(id, name, password);

		// userテーブルへの反映（INSERT）
		accountRepository.save(user);

		// 「/users」にGETでリクエストし直せ（リダイレクト）
		return "redirect:/account";
	}

}
