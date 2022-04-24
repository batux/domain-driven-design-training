package com.trendyol.pos.management.controller;

import com.trendyol.payment.domain.model.PosSelection;
import com.trendyol.pos.management.application.PosSelectionHandler;
import com.trendyol.pos.management.domain.model.Pos;
import com.trendyol.pos.management.domain.model.PosDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pos")
public class PosController {

    private final PosSelectionHandler handler;

    public PosController(PosSelectionHandler handler) {
        this.handler = handler;
    }

    @GetMapping("/{posId}")
    public ResponseEntity<Pos> fetchById(@PathVariable("posId") String posId) {
        return ResponseEntity.ok(this.handler.findById(posId));
    }

    @PostMapping("/list")
    public ResponseEntity<List<Pos>> fetchList(@RequestBody PosSelection posSelection) {
        return ResponseEntity.ok(this.handler.fetchValidList(posSelection));
    }

    @PostMapping("/detail/list")
    public ResponseEntity<List<PosDetail>> fetchDetailList(@RequestBody PosSelection posSelection) {
        return ResponseEntity.ok(this.handler.fetchDetailList(posSelection));
    }
}
