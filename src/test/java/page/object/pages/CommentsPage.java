package page.object.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import page.object.BaseFunc;

import static org.openqa.selenium.By.*;

public class CommentsPage {
    private final By COMMENTS_PAGE_TITLE = xpath(".//h1[@class = 'article-title']/a");
    private final By COMMENTS_PAGE_COMMENTS_COUNT = By.xpath(".//span[(@class = 'type-cnt')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private BaseFunc baseFunc;

    public CommentsPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getTitle() {
        LOGGER.info("Getting article title");

        return baseFunc.getText(COMMENTS_PAGE_TITLE);
    }

    public Integer getCommentsPageComments() {
        LOGGER.info("Getting comment count");

        String commentsPageComments = baseFunc.findElement(COMMENTS_PAGE_COMMENTS_COUNT).getText();
        commentsPageComments = commentsPageComments.substring(1, commentsPageComments.length() -1);
        return Integer.valueOf(commentsPageComments);
    }

}
