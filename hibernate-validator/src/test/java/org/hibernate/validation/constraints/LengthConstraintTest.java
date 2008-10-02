// $Id: LengthConstraintTest.java 110 2008-09-29 23:46:37Z epbernard $
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
package org.hibernate.validation.constraints;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * @author Hardy Ferentschik
 */
public class LengthConstraintTest {
	@Test
	public void testIsValid() {
		LengthConstraint constraint = new LengthConstraint();
		constraint.initialize(
				new Length() {

					public int min() {
						return 1;
					}

					public int max() {
						return 3;
					}

					public String message() {
						return "{validator.length}";
					}

					public String[] groups() {
						return new String[0];
					}

					public Class<? extends Annotation> annotationType() {
						return this.getClass();
					}
				}
		);

		assertTrue( constraint.isValid( null, null ) );
		assertFalse( constraint.isValid( "", null ) );
		assertTrue( constraint.isValid( "f", null ) );
		assertTrue( constraint.isValid( "fo", null ) );
		assertTrue( constraint.isValid( "foo", null ) );
		assertFalse( constraint.isValid( "foobar", null ) );

		try {
			constraint.isValid( new Object(), null );
			fail();
		}
		catch ( IllegalArgumentException e ) {
			// success
		}
	}
}
