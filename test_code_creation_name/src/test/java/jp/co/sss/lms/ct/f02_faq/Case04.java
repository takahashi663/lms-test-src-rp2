package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加
		//画面遷移
				goTo("http://localhost:8080/lms");

				//タイトルが一致しているかどうか
				String title = webDriver.getTitle();
				assertEquals("ログイン | LMS", title);

				//ログイン画面のエビデンス取得
				getEvidence(new Object() {});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		//ログインId パスワード ログインボタンを取得
				final WebElement loginId = webDriver.findElement(By.name("loginId"));
				final WebElement password = webDriver.findElement(By.name("password"));
				final WebElement login = webDriver.findElement(By.className("btn"));
		
		String Id = "StudentAA01";
		String pass = "StudentAA0";
		
		loginId.clear();
		loginId.sendKeys(Id);
		password.clear();
		password.sendKeys(pass);
		
		//ログインボタンを押す
		
		login.click();
		

		//ログイン後の表示待ち
		visibilityTimeout(By.className("btn"), 10);
		
		//エビデンス取得
		getEvidence(new Object() {});
		
		//ログインできているか確認
		final String loginUser = webDriver.findElement(By.tagName("small")).getText();
		assertEquals("ようこそ受講生ＡＡ１さん", loginUser);
		
		//ログイン後の表示待ち
				visibilityTimeout(By.className("btn"), 10);
				getEvidence(new Object() {
				});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		//ヘルプリンクに行くまでの機能ボタンを押す
		// 上部メニューの機能リンクを取得し押下
				final WebElement menu = webDriver.findElement(By.className("dropdown-toggle"));
				menu.click();
				
				// 上部メニューのエビデンス取得
				getEvidence(new Object() {}, "menu");
				
				// メニューの中のヘルプリンクを取得し押下
				final WebElement helpLink = webDriver.findElement(By.linkText("ヘルプ"));
				helpLink.click();

				// ヘルプ画面のエビデンス取得
				getEvidence(new Object() {}, "help");

				// 遷移先URLが合っているか
				String url = webDriver.getCurrentUrl();
				assertEquals("http://localhost:8080/lms/help", url);
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() throws InterruptedException {
		// TODO ここに追加
		//「よくある質問」リンクボタンを押す
		// URLを取得し押す
				final WebElement questionLink = webDriver.findElement(By.linkText("よくある質問"));	
				questionLink.click();

				// 別タブで開く
				Object[] windowHandles = webDriver.getWindowHandles().toArray();
				webDriver.switchTo().window((String) windowHandles[1]);
				
				// 遷移後のエビデンス取得
				getEvidence(new Object() {});
				
				// 遷移先URLの一致確認
				String url = webDriver.getCurrentUrl();
				assertEquals("http://localhost:8080/lms/faq", url);
	}

}
