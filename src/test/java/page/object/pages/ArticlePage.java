package page.object.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import page.object.BaseFunc;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.By.*;

public class ArticlePage {
    private final By ARTICLE_PAGE_TITLE = xpath(".//h1[contains(@class, 'd-inline')]");
    private final By ARTICLE_PAGE_COMMENTS = xpath(".//a[contains(@class, 'text-size-19')]");
    private final By TIME = xpath(".//time[contains(@class, 'd-block')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private BaseFunc baseFunc;

    public ArticlePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;

        assertTrue(baseFunc.isElementPresents(TIME), "There is no time on page");
        LOGGER.info("We are on article page");
    }

    public String getTitle() {
        LOGGER.info("Getting article title");

        return baseFunc.getText(ARTICLE_PAGE_TITLE).trim();
    }

    public Integer getCommentCount() {
        LOGGER.info("Getting comments count");

        String comments = baseFunc.findElement(ARTICLE_PAGE_COMMENTS).getText();
        comments = comments.substring(1, comments.length() - 1);
        return Integer.valueOf(comments);
    }

    public CommentsPage openComments() {
        LOGGER.info("Trying to open comments page");

        baseFunc.click(ARTICLE_PAGE_COMMENTS);
        return new CommentsPage(baseFunc);
    }

}
