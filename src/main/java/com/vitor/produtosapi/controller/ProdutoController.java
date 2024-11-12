package com.vitor.produtosapi.controller;

import com.vitor.produtosapi.model.Produto;
import com.vitor.produtosapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // inserir produto
    // gerar um id criptografado com UUID
    @PostMapping("/save")
    public Produto save(@RequestBody Produto produto) {
        System.out.println("Produto recebido: " + produto);

        var id = UUID.randomUUID().toString();
        produto.setId(id);

        produtoRepository.save(produto);
        return produto;
    }

    // buscar produto por id
    @GetMapping("/findById/{id}")
    public Produto findById(@PathVariable String id) {
        return produtoRepository.findById(id).orElse(null);
    }

    // buscar lista de produtos3
    @GetMapping("/findAll")
    public List<Produto> findAll(Produto produto) {
        return produtoRepository.findAll();
    }

    // deletar produto por id
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        produtoRepository.deleteById(id);
    }

    // atualizar produto por id
    @PutMapping("/update/{id}")
    public void updateById(@PathVariable String id,
                           @RequestBody Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
    }

    // buscar produto por parametro. Ex: nome
    @GetMapping("/findByName")
    public List<Produto> findByNome(@RequestParam("nome") String nome) {
        return produtoRepository.findByNome(nome);
    }

}
