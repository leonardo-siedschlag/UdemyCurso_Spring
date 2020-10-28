package com.example.curso.entities.enums;

public enum OrderStatus {
	WAITING_PAYMENT(1), PAID(2), SHIPPED(3), DELIVERED(4), CANCELED(5);

	private int cod;

	private OrderStatus(int cod) {
		this.cod = cod;

	}

	public int getCode() {
		return cod;

	}

	// static nao precisa instaciar
	public static OrderStatus valueOf(int code) {
		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;// PAYMENT EM VEZ DO NUMERO
			}
		}
		throw new IllegalArgumentException("Codigo invalido!");
	}

}
