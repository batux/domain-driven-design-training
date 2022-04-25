package com.trendyol.pos.management.domain.service;

import com.trendyol.pos.management.domain.model.Pos;
import com.trendyol.pos.management.domain.model.PosDetail;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// <<Domain Service>>
@Service
public class PosDetailsService {

    public List<PosDetail> prepareList(List<Pos> posList) {
        if(CollectionUtils.isEmpty(posList)) {
            return Collections.emptyList();
        }
        return posList.stream()
                .filter(Pos::isEnabled)
                .map(Pos::buildDetails)
                .collect(Collectors.toList());
    }
}
