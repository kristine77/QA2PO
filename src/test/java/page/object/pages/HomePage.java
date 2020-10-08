package page.object.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.object.BaseFunc;

import java.util.List;

public class HomePage {
    private final By HOME_PAGE_ARTICLE = By.tagName("article");
    private final By TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By ARTICLE = By.xpath(".//span[contains(@class, 'text-size-16')]");
    private final By COMMENT_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    BaseFunc baseFunc;

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public WebElement getArticleById(int id) {
        return baseFunc.findElements(ARTICLE).get(id);
    }

    public String getTitleById(int id) {
        LOGGER.info("Getting article title on home page");

        return getHomePageArticleById(id).findElement(TITLE).getText().trim();
    }

    public Integer getCommentCountById(int id) {
        LOGGER.info("Getting comment count on home page");

        WebElement article = getArticleById(id);
        String commentCount = article.findElement(COMMENT_COUNT).getText();

        commentCount = commentCount.substring(1, commentCount.length() - 1);
        return Integer.valueOf(commentCount);
    }

    //Opening Article Page
    public ArticlePage openArticle(int id) {
        LOGGER.info("Opening article page");

        baseFunc.click(getHomePageArticleById(id));
        return new ArticlePage(baseFunc);
    }

    public WebElement getHomePageArticleById(int id) {
        List<WebElement> articles = baseFunc.findElements(HOME_PAGE_ARTICLE);
        Assertions.assertFalse(articles.isEmpty(), "There are no articles");
        return articles.get(id);
    }

}
