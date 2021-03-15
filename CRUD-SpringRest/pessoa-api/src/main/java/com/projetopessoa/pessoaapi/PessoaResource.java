package com.projetopessoa.pessoaapi;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pr;
	
	 @GetMapping
    public List<Pessoa> listarPessoas(){
	 return this.pr.findAll();
    }
		 
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	 public Pessoa addPessoa(@RequestBody Pessoa pessoa){
		return this.pr.save(pessoa);
	}
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<Pessoa> buscarPessoa(@PathVariable Long id){
		 Optional<Pessoa> pessoa = pr.findById(id);
		 
		 if(pessoa.isPresent()) {
			 return ResponseEntity.ok(pessoa.get());
		 }
	 
		 return ResponseEntity.notFound().build();
    }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id,
			 @RequestBody Pessoa pessoa){
		
		 if (!pr.existsById(id)) {
			 return ResponseEntity.notFound().build();
		 }
		
		pessoa.setId(id);
		pessoa = pr.save(pessoa);
		 
		 return ResponseEntity.ok(pessoa);
	 }
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> deletarPessoa(@PathVariable Long id){
		 
		 if (!pr.existsById(id)) {
			 return ResponseEntity.notFound().build();
		 }
		 
		pr.deleteById(id);
		 
		 return ResponseEntity.noContent().build();
	 }
	 


}
