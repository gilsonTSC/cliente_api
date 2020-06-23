package com.gilsontsc.clientes.service.exception;

public class UsuarioCadastradoException extends RuntimeException{

	private static final long serialVersionUID = -2248290949583462542L;

	public UsuarioCadastradoException(String login) {
		super("Usuário já cadastrado para o login " + login);
	}
	
}