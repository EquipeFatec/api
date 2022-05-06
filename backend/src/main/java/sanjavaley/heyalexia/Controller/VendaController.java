package sanjavaley.heyalexia.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<String> topSellOrderByQuantidade(){
        List<String> list = new ArrayList<>();
        list.add(service.valorTotal());
        list.add(vendaService.vendaTotal());
        return list;
    }
}
