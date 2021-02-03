package entity;

import com.kyzh.pojo.TbArticle;
import com.kyzh.pojo.TbArticleContent;

import java.io.Serializable;

/**
 * 封装的文章对象（文章+文章内容）
 */
public class Article implements Serializable {

    //文章对象
    private TbArticle article;
    //文章内容对象
    private TbArticleContent articleContent;

    public TbArticle getArticle() {
        return article;
    }

    public void setArticle(TbArticle article) {
        this.article = article;
    }

    public TbArticleContent getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(TbArticleContent articleContent) {
        this.articleContent = articleContent;
    }
}
