import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;
import parissaclay.ter.quiz.pojo.${pojo.getDeclarationName()};
// Generated ${date} by Hibernate Tools ${version}

<#assign date = false>
<#assign string = false>
<#foreach field in pojo.getAllPropertiesIterator()>
	<#if "${pojo.getJavaTypeName(field, jdk5)}"?contains("Date")>
		<#if !date>
			import java.util.Calendar;
			<#assign date = true>
		</#if>
	</#if>
	<#if "${pojo.getJavaTypeName(field, jdk5)}"?contains("String")>
		<#if !string>
			import org.apache.commons.lang3.RandomStringUtils;
			<#assign string = true>
		</#if>
	</#if>
</#foreach>

<#assign classbody>
${pojo.getClassModifiers()} ${pojo.getDeclarationType()} Test${pojo.getDeclarationName()}
{
    <#-- // Property accessors -->
	<#foreach field in pojo.getAllPropertiesIterator()>
		<#if "${pojo.getJavaTypeName(field, jdk5)}"?contains("Set")>
	 	<#else>
		<#switch pojo.getJavaTypeName(field, jdk5)>
			<#case "String">
			@Test
			public void testSet${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
				int randomLength = ThreadLocalRandom.current().nextInt(1, 10);
				String randomString = RandomStringUtils.random(randomLength, true, true);
				pojo.set${pojo.getPropertyName(field)}(randomString);
	
				final Field field = pojo.getClass().getDeclaredField("${field.name}");
				field.setAccessible(true);
				assertEquals(field.get(pojo), randomString);
			}
			
			@Test
			public void testGet${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
		        final Field field = pojo.getClass().getDeclaredField("${field.name}");
		        field.setAccessible(true);
		        int randomLength = ThreadLocalRandom.current().nextInt(1, 10);
				String randomString = RandomStringUtils.random(randomLength, true, true);
		        field.set(pojo, randomString);
	
	        	assertEquals(pojo.get${pojo.getPropertyName(field)}(), randomString);
			}
			    <#break>
			    <#case "Integer">
			@Test
			public void testSet${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
				int random = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
				pojo.set${pojo.getPropertyName(field)}(random);
	
				final Field field = pojo.getClass().getDeclaredField("${field.name}");
				field.setAccessible(true);
				assertEquals(field.get(pojo), random);
			}
			
			@Test
			public void testGet${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
		        final Field field = pojo.getClass().getDeclaredField("${field.name}");
		        field.setAccessible(true);
		        int random = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
		        field.set(pojo, random);
	
	        	assertEquals(pojo.get${pojo.getPropertyName(field)}(), random);
			}
			    <#break>
			     <#case "int">
			@Test
			public void testSet${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
				int random = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
				pojo.set${pojo.getPropertyName(field)}(random);
	
				final Field field = pojo.getClass().getDeclaredField("${field.name}");
				field.setAccessible(true);
				assertEquals(field.get(pojo), random);
			}
			
			@Test
			public void testGet${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
		        final Field field = pojo.getClass().getDeclaredField("${field.name}");
		        field.setAccessible(true);
		        int random = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
		        field.set(pojo, random);
	
	        	assertEquals(pojo.get${pojo.getPropertyName(field)}(), random);
			}
			    <#break>
			    <#case "boolean">
			@Test
			public void testSet${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
				boolean randomBoolean = ThreadLocalRandom.current().nextBoolean();
				pojo.set${pojo.getPropertyName(field)}(randomBoolean);

				final Field field = pojo.getClass().getDeclaredField("${field.name}");
				field.setAccessible(true);
				assertEquals(field.get(pojo), randomBoolean);
			}
			
			@Test
			public void testGet${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
		        final Field field = pojo.getClass().getDeclaredField("${field.name}");
		        field.setAccessible(true);
		        boolean randomBoolean = ThreadLocalRandom.current().nextBoolean();
		        field.set(pojo, randomBoolean);
	
	        	assertEquals(pojo.get${pojo.getPropertyName(field)}(), randomBoolean);
			}
			    <#break>
			    <#case "Date">
			@Test
			public void testSet${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
				Calendar calendar = Calendar.getInstance();
				java.sql.Date today = new java.sql.Date(calendar.getTimeInMillis());
				pojo.set${pojo.getPropertyName(field)}(today);
				pojo.set${pojo.getPropertyName(field)}(new java.sql.Date(calendar.getTimeInMillis()));
	
				final Field field = pojo.getClass().getDeclaredField("${field.name}");
				field.setAccessible(true);
				assertEquals(field.get(pojo), today);
			}
			
			@Test
			public void testGet${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
		        final Field field = pojo.getClass().getDeclaredField("${field.name}");
		        field.setAccessible(true);
		        Calendar calendar = Calendar.getInstance();
				java.sql.Date today = new java.sql.Date(calendar.getTimeInMillis());
		        field.set(pojo, today);
	
	        	assertEquals(pojo.get${pojo.getPropertyName(field)}(), today);
			}
			    <#break>
			     <#case "float">
			@Test
			public void testSet${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
				pojo.set${pojo.getPropertyName(field)}(1.0f);
	
				final Field field = pojo.getClass().getDeclaredField("${field.name}");
				field.setAccessible(true);
				assertEquals(field.get(pojo), 1.0f);
			}
			
			@Test
			public void testGet${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
		        final Field field = pojo.getClass().getDeclaredField("${field.name}");
		        field.setAccessible(true);
		        field.set(pojo, 1.0f);
	
	        	assertEquals(pojo.get${pojo.getPropertyName(field)}(), 1.0f);
			}
			    <#break>
			  <#default>
			  	<#if "${pojo.getPropertyName(field)}"?contains("By")>
					@Test
			public void testSet${"${pojo.getPropertyName(field)}"?substring("${pojo.getPropertyName(field)}"?index_of("By") + 2)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
				pojo.set${"${pojo.getPropertyName(field)}"?substring("${pojo.getPropertyName(field)}"?index_of("By") + 2)}(1);
	
				final Field field = pojo.getClass().getDeclaredField("id${"${pojo.getPropertyName(field)}"?substring("${pojo.getPropertyName(field)}"?index_of("By") + 4)}");
				field.setAccessible(true);
				assertEquals(field.get(pojo), 1);
			}
			
			@Test
			public void testGet${"${pojo.getPropertyName(field)}"?substring("${pojo.getPropertyName(field)}"?index_of("By") + 2)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
		        final Field field = pojo.getClass().getDeclaredField("id${"${pojo.getPropertyName(field)}"?substring("${pojo.getPropertyName(field)}"?index_of("By") + 4)}");
		        field.setAccessible(true);
		        field.set(pojo, 1);
	
	        	assertEquals(pojo.get${"${pojo.getPropertyName(field)}"?substring("${pojo.getPropertyName(field)}"?index_of("By") + 2)}(), 1);
			}
			  	<#else>
			  		 @Test
			public void testSetId${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
				pojo.setId${pojo.getPropertyName(field)}(1);
	
				final Field field = pojo.getClass().getDeclaredField("id${pojo.getPropertyName(field)}");
				field.setAccessible(true);
				assertEquals(field.get(pojo), 1);
			}
			
			@Test
			public void testGetId${pojo.getPropertyName(field)}() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
			{
				final ${pojo.getDeclarationName()} pojo = new ${pojo.getDeclarationName()}();
		        final Field field = pojo.getClass().getDeclaredField("id${pojo.getPropertyName(field)}");
		        field.setAccessible(true);
		        field.set(pojo, 1);
	
	        	assertEquals(pojo.getId${pojo.getPropertyName(field)}(), 1);
			}
			  	</#if>
			    <#break>
			</#switch>

  		
		</#if>
			
	</#foreach>
}
</#assign>

${classbody}

