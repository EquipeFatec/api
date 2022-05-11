package sanjavaley.heyalexia.Controller;


import oracle.sql.DATE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sanjavaley.heyalexia.Service.ItemVendaServiceImp;
import sanjavaley.heyalexia.Service.VendaServiceImp;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/venda")
public class VendaController {

    @Autowired
    private ItemVendaServiceImp service;
    @Autowired
    private VendaServiceImp vendaService;

    @GetMapping(value = "/total")
    public List<String> topSellOrderByQuantidade(@RequestParam (name="mes",required = false) Integer mes){
        List<String> list = new ArrayList<>();
        list.add(service.valorTotal(mes));
        list.add(vendaService.vendaTotal(mes));
        return list;
    }

    @GetMapping(value = "/valor-total")
    @ResponseBody
    public String valorVendaTotalPorData(@RequestParam String inicial, @RequestParam String fim){
        String total = vendaService.valorVendaPorData(inicial, fim);
        if (total == null){
            return "Sem dados entre essas datas!!";
        }else{
            return total;
        }

    }
}
