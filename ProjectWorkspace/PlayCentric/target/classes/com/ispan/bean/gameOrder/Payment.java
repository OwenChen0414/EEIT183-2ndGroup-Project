package com.ispan.bean.gameOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity @Table(name = "payment")
public class Payment {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	
	private String paymentName;
	
	@OneToMany(mappedBy = "payment",fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,orphanRemoval = true)
	private Recharge recharge;
}
