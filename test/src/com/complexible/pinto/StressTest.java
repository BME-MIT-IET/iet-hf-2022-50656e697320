/*
 * Copyright (c) 2015 Complexible Inc. <http://complexible.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.complexible.pinto;

import com.complexible.common.openrdf.model.ModelIO;
import com.complexible.pinto.annotations.RdfProperty;
import com.complexible.pinto.impl.IdentifiableImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openrdf.model.*;
import org.openrdf.model.util.Models;
import org.openrdf.rio.RDFFormat;

import java.io.*;
import java.util.*;

import javax.lang.model.element.TypeParameterElement;

import static org.junit.Assert.assertTrue;

/**
 * <p></p>
 *
 * @author
 */
public class StressTest {

	private static TimeZone tz = TimeZone.getDefault();

	@BeforeClass
	public static void setup() {
		// so that serialized dates get same TZ as reference data, not local TZ
		TimeZone.setDefault(TimeZone.getTimeZone("US/Eastern"));
	}

	@AfterClass
	public static void teardown() {
		TimeZone.setDefault(tz);
	}

	@Test
	public void stressTest() throws Exception {
		ClassWithObjectList aObj = new ClassWithObjectList();
		List<Person> people = new ArrayList<Person>() {};
		for (int i = 0; i < 300000; i++) {
			people.add(new Person("" + i));
		}
		aObj.setList(Lists.newArrayList(people));
		Model aResult = RDFMapper.create().writeValue(aObj);

		assertTrue(Models.isomorphic(ModelIO.read(RDFMapperTests.Files3.classPath("/data/stresstest.nt").toPath()), aResult));
	}

	public static final class Person implements Comparable<Person>, Identifiable {
		private String mName;

		private Identifiable mIdentifiable = new IdentifiableImpl();

		public Person() {
		}

		public Person(final String theName) {
			mName = theName;
		}

		@Override
		public String toString() {
			return mName;
		}

		@Override
		public Resource id() {
			return mIdentifiable.id();
		}

		@Override
		public void id(final Resource theResource) {
			mIdentifiable.id(theResource);
		}

		public String getName() {
			return mName;
		}

		public void setName(final String theName) {
			mName = theName;
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(mName);
		}

		@Override
		public boolean equals(final Object theObj) {
			if (theObj == this) {
				return true;
			}
			else if (theObj instanceof Person) {
				return Objects.equals(mName, ((Person) theObj).mName);
			}
			else {
				return false;
			}
		}

		@Override
		public int compareTo(final Person thePerson) {
			return mName.compareTo(thePerson.getName());
		}
	}


	/**
	 * We can get collections which have a different order than the original when we don't use the rdf:list
	 * serialized collection.  But order will matter in an .equals call.  in real usage, if order matters,
	 * users will use the rdf:list version.  for now, we just want to make sure the collections have the same
	 * elements and are of the same type.  that's sufficient.
	 */
	private static <T> boolean equalsTypeAndContents(Collection<T> theCollection, final Collection<T> theOther) {
		return (theCollection == null && theOther == null)
		       || (theCollection != null && theOther != null
		           && theCollection.getClass() == theOther.getClass()
		           && Sets.newHashSet(theCollection).equals(Sets.newHashSet(theOther)));
	}

	public static class ClassWithObjectList implements Identifiable {
		private List<Person> mList = Lists.newArrayList();

		private Set<Person> mSet = Sets.newLinkedHashSet();

		private Collection<Person> mCollection = Sets.newLinkedHashSet();

		private SortedSet<Person> mSortedSet = Sets.newTreeSet();

		private Identifiable mIdentifiable = new IdentifiableImpl();

		@Override
		public Resource id() {
			return mIdentifiable.id();
		}

		@Override
		public void id(final Resource theResource) {
			mIdentifiable.id(theResource);
		}

		@Override
		public int hashCode() {
			return Objects.hash(mList, mSet, mCollection, mSortedSet);
		}

		@Override
		public boolean equals(final Object theObj) {
			if (theObj == this) {
				return true;
			}
			else if (theObj instanceof ClassWithObjectList) {
				ClassWithObjectList aObj = (ClassWithObjectList) theObj;

				return equalsTypeAndContents(mList, aObj.mList)
				       && equalsTypeAndContents(mSet, aObj.mSet)
				       && equalsTypeAndContents(mCollection, aObj.mCollection)
				       && equalsTypeAndContents(mSortedSet, aObj.mSortedSet);
			}
			else {
				return false;
			}
		}

		public Collection<Person> getCollection() {
			return mCollection;
		}

		public void setCollection(final Collection<Person> theCollection) {
			mCollection = theCollection;
		}

		public List<Person> getList() {
			return mList;
		}

		public void setList(final List<Person> theList) {
			mList = theList;
		}

		public Set<Person> getSet() {
			return mSet;
		}

		public void setSet(final Set<Person> theSet) {
			mSet = theSet;
		}

		public SortedSet<Person> getSortedSet() {
			return mSortedSet;
		}

		public void setSortedSet(final SortedSet<Person> theSortedSet) {
			mSortedSet = theSortedSet;
		}
	}

	public static class ClassWithRdfObjectList implements Identifiable {
		private List<Person> mList = Lists.newArrayList();

		private Identifiable mIdentifiable = new IdentifiableImpl();

		@Override
		public Resource id() {
			return mIdentifiable.id();
		}

		@Override
		public void id(final Resource theResource) {
			mIdentifiable.id(theResource);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(mList);
		}

		@Override
		public boolean equals(final Object theObj) {
			if (theObj == this) {
				return true;
			}
			else if (theObj instanceof ClassWithRdfObjectList) {
				ClassWithRdfObjectList aObj = (ClassWithRdfObjectList) theObj;

				return Objects.equals(mList, aObj.mList);
			}
			else {
				return false;
			}
		}

		@RdfProperty(isList = true)
		public List<Person> getList() {
			return mList;
		}

		public void setList(final List<Person> theList) {
			mList = theList;
		}
	}
}
