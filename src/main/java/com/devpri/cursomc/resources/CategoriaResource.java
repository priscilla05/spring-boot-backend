package com.devpri.cursomc.resources;

import com.devpri.cursomc.domain.Categoria;
import com.devpri.cursomc.dto.CategoriaDTO;
import com.devpri.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id){

        Categoria objeto = categoriaService.find(id);
        return ResponseEntity.ok().body(objeto);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert (@RequestBody Categoria obj){
        obj = categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Categoria obj,@PathVariable Integer id){
        obj.setId(id);
        obj = categoriaService.update(obj);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();


    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity <List<CategoriaDTO>> findAll(){
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaDTO> listDto = list.stream().map(objeto-> new CategoriaDTO(objeto)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);

    }

    @RequestMapping( value = "/page", method = RequestMethod.GET)
    public ResponseEntity <Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam (value = "linesPage", defaultValue = "24") Integer linesPerPage,
                                                        @RequestParam (value = "orderBy", defaultValue = "nome") String orderBy,
                                                        @RequestParam (value = "direction", defaultValue = "ASC")String direction){
        Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> listDto = list.map(objeto-> new CategoriaDTO(objeto));
        return ResponseEntity.ok().body(listDto);

    }

}
