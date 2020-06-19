package com.gilsontsc.clientes.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.gilsontsc.clientes.model.entity.Cliente;
import com.gilsontsc.clientes.model.entity.ServicoPrestado;
import com.gilsontsc.clientes.model.repository.ClienteRepository;
import com.gilsontsc.clientes.model.repository.ServicoPrestadoRepository;
import com.gilsontsc.clientes.rest.dto.ServicoPrestadoDTO;
import com.gilsontsc.clientes.util.BigDecimalConverter;

@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ServicoPrestadoRepository repository;
	
	@Autowired
	private BigDecimalConverter bigDecimalConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();
		
		Cliente cliente = this.clienteRepository
				.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente"));
				
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(this.bigDecimalConverter.converter(dto.getPreco()));
		
		return this.repository.save(servicoPrestado);		
	}
	
	@GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ) {
        return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }
	
}