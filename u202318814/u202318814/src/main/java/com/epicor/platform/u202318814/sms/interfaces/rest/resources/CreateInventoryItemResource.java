package com.epicor.platform.u202318814.sms.interfaces.rest.resources;

public record CreateInventoryItemResource(String epicorSku, Double minimumQuantity,
                                          Double availableQuantity) {}
