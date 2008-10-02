// $Id: ValidatorFactoryImpl.java 105 2008-09-29 12:37:32Z hardy.ferentschik $
/*
* JBoss, Home of Professional Open Source
* Copyright 2008, Red Hat Middleware LLC, and individual contributors
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.hibernate.validation.impl;

import javax.validation.ConstraintFactory;
import javax.validation.MessageResolver;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.spi.ValidatorBuilderImplementor;

import org.hibernate.validation.engine.ValidatorImpl;

/**
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 */
public class ValidatorFactoryImpl implements ValidatorFactory {

	private final MessageResolver messageResolver;
	private final ConstraintFactory constraintFactory;


	public ValidatorFactoryImpl(ValidatorBuilderImplementor validatorBuilder) {
		this.messageResolver = validatorBuilder.getMessageResolver();
		this.constraintFactory = validatorBuilder.getConstraintFactory();
		//do init metadata from XML form
	}

	/**
	 * {@inheritDoc}
	 */
	public <T> Validator<T> getValidator(Class<T> clazz) {
		return new ValidatorImpl<T>( clazz, constraintFactory, messageResolver );
	}
}
