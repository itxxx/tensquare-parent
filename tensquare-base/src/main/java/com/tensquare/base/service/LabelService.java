package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uitl.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;
    public List<Label> findAll(){
        return labelDao.findAll();
    }
    public Label findById(String id){
        return labelDao.findById(id).get();
    }
    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }
    public void update(Label label){
        labelDao.save(label);
    }
    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List list = new ArrayList();
                if (label.getLabelname()!=null&&!"".equals(label.getLabelname())){
                    Predicate predicate=criteriaBuilder.like(root.get("labelname").as(String.class), label.getLabelname());
                    list.add(predicate);
                }
                if (label.getState()!=null&&"".equals(label.getState())){
                    Predicate predicate=criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }

                Predicate[] pre = new Predicate[list.size()];
                list.toArray(pre);
                return criteriaBuilder.and(pre);
            }
        });
    }

    public Page pageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List list = new ArrayList();
                if (label.getLabelname()!=null&&!"".equals(label.getLabelname())){
                    Predicate predicate=criteriaBuilder.like(root.get("labelname").as(String.class), label.getLabelname());
                    list.add(predicate);
                }
                if (label.getState()!=null&&"".equals(label.getState())){
                    Predicate predicate=criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }

                Predicate[] pre = new Predicate[list.size()];
                list.toArray(pre);
                return criteriaBuilder.and(pre);
            }
        },pageable);
    }
}
