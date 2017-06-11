package com.olebokolo.distance.model.repository;

import com.olebokolo.distance.model.entity.Entry;
import com.olebokolo.distance.model.entity.Rule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends CrudRepository<Rule, Long> {
    Rule findByEntry(Entry entry);
}
