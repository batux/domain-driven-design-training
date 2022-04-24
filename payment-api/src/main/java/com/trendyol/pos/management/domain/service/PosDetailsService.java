package com.trendyol.pos.management.domain.service;

import com.trendyol.pos.management.domain.model.Pos;
import com.trendyol.pos.management.domain.model.PosDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// <<Domain Service>>
@Service
public class PosDetailsService {

    public List<PosDetail> prepareList(List<Pos> posList) {
        List<PosDetail> posDetailList = new ArrayList<>();
        for(Pos pos : posList) {
            if(!pos.isEnabled()) {
                continue;
            }
            posDetailList.add(pos.buildDetails());
        }
        return posDetailList;
    }
}
