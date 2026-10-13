package com.instituto.endoscopia.controller;

import com.instituto.endoscopia.model.*;
import com.instituto.endoscopia.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gases")
@CrossOrigin(origins = "*")
public class GasController {

    @Autowired
    private ConteoGasRepository conteoGasRepository;

    @Autowired
    private CompraGasRepository compraGasRepository;

    @Autowired
    private StockGasInicialRepository stockGasInicialRepository;

    @Autowired
    private ConsumoGasRepository consumoGasRepository;

    // --- 1. CONTEO VISUAL DE SALA ---
    @GetMapping("/conteo")
    public List<ConteoGas> obtenerConteos() {
        return conteoGasRepository.findAll();
    }

    @PostMapping("/conteo")
    public ConteoGas guardarConteo(@RequestBody ConteoGas conteo) {
        return conteoGasRepository.save(conteo);
    }

    // --- 2. COMPRAS Y REMITOS ---
    @GetMapping("/compras")
    public List<CompraGas> obtenerCompras() {
        return compraGasRepository.findAll();
    }

    @PostMapping("/compras")
    public CompraGas guardarCompra(@RequestBody CompraGas compra) {
        return compraGasRepository.save(compra);
    }

    // --- 3. STOCK INICIAL ---
    @GetMapping("/stock-inicial")
    public List<StockGasInicial> obtenerStocksIniciales() {
        return stockGasInicialRepository.findAll();
    }

    @PostMapping("/stock-inicial")
    public StockGasInicial guardarStockInicial(@RequestBody StockGasInicial stock) {
        return stockGasInicialRepository.save(stock);
    }

    // --- 4. HISTORIAL DE CONSUMOS (DIFERENCIA) ---
    @GetMapping("/consumo-historial")
    public List<ConsumoGas> obtenerConsumos() {
        return consumoGasRepository.findAll();
    }

    @PostMapping("/consumo-historial")
    public ConsumoGas guardarConsumo(@RequestBody ConsumoGas consumo) {
        return consumoGasRepository.save(consumo);
    }
}