package net.bendi.onabcn.business.dto;

public interface Transformer<S, T> {
	
	T transform(S source);
	
}
