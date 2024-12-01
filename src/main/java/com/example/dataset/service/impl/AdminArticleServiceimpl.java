package com.example.dataset.service.impl;

import com.example.dataset.DTO.SetArticleStatusDTO;
import com.example.dataset.VO.ArticleWithoutAuditListVO;
import com.example.dataset.mapper.AdminArticleMapper;
import com.example.dataset.service.AdminArticleService;
import com.example.dataset.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminArticleServiceimpl  implements AdminArticleService {

    @Autowired
    private AdminArticleMapper adminArticleMapper;

    @Autowired
    private MaterialServiceimpl materialServiceimpl;

    @Override
    @Transactional
    public PageResult getArticleWithoutAudit(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ArticleWithoutAuditListVO> pages = adminArticleMapper.getArticleWithoutAudit();

        if (pages != null && pages.getResult() != null) {
            // 遍历pages中的每个ArticleWithoutAuditListVO实例
            for (ArticleWithoutAuditListVO article : pages.getResult()) {
                // 修改属性，例如设置一个新的值
                article.setUrl(materialServiceimpl.download(article.getId()));
            }
        }
        return new PageResult(pages.getTotal(), pages.getResult());
    }

    @Override
    public void setArticleStatus(SetArticleStatusDTO setArticleStatusDTO) {
        adminArticleMapper.setArticleStatus(setArticleStatusDTO.getId(), setArticleStatusDTO.getPass());
    }
}
