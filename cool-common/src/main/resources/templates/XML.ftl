<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.cool.dao.${table_name}Mapper">
	<resultMap type="com.cool.model.${table_name}" id="resultMap">
		<id column="id" property="id"/>
<#if model_column?exists>
	<#list model_column as model>
		<result column="${model.columnName}" property="${model.changeColumnName?uncap_first}"/>
	</#list>
</#if>
		<result column="enable" property="enable"/>
		<result column="remark" property="remark"/>
		<result column="create_by" property="createBy"/>
		<result column="create_time" property="createTime"/>
		<result column="update_by" property="updateBy"/>
		<result column="update_time" property="updateTime"/>
	</resultMap>
	<sql id="allFields">
	id,
		<#if model_column?exists>
			<#list model_column as model>
				${model.columnName},
			</#list>
		</#if>
	enable,remark,create_by,create_time,update_by,update_time
	</sql>
	<insert id="insert" useGeneratedKeys="true" keyProperty="id" parameterType="Object">
		insert into ${table}
		<trim prefix="(" suffix=")" suffixOverrides="," >
			<if test="id != null">
				id,
			</if>
<#if model_column?exists>
	<#list model_column as model>
		<#if (model.columnType = 'VARCHAR' || model.columnType = 'TEXT')>
			<if test="${model.changeColumnName?uncap_first} != null and ${model.changeColumnName?uncap_first} != ''">
				${model.columnName},
			</if>
		<#else>
			<if test="${model.changeColumnName?uncap_first} != null">
				${model.columnName},
			</if>
		</#if>
	</#list>
</#if>
			<if test="enable != null">
				 enable,
			</if>
			<if test="remark != null and remark != ''">
				remark,
			</if>
			<if test="createBy != null and createBy != ''">
				create_by,
			</if>
			<if test="createTime != null">
				create_time,
			</if>
		</trim>
		values 
		<trim prefix="(" suffix=")" suffixOverrides="," >
			<if test="id != null">
				${"#{"}id},
			</if>
<#if model_column?exists>
	<#list model_column as model>
		<#if (model.columnType = 'VARCHAR' || model.columnType = 'TEXT')>
			<if test="${model.changeColumnName?uncap_first} != null and ${model.changeColumnName?uncap_first} != ''">
				${"#{"}${model.changeColumnName?uncap_first}},
			</if>
		<#else>
			<if test="${model.changeColumnName?uncap_first} != null">
				${"#{"}${model.changeColumnName?uncap_first}},
			</if>
		</#if>
	</#list>
</#if>
			<if test="enable != null">
				 ${"#{"}enable},
			</if>
			<if test="remark != null and remark != ''">
				${"#{"}remark},
			</if>
			<if test="createBy != null and createBy != ''">
				${"#{"}createBy},
			</if>
			<if test="createTime != null">
				${"#{"}createTime},
			</if>
		</trim>
	</insert>
	<!-- 删除记录 -->
	<delete id="deleteByPrimaryKey" parameterType="Object">
		delete from ${table} where id = ${"#{"}id}
	</delete>

	<!-- 根据id查询 -->
	<select id="selectByPrimaryKey"  resultMap="resultMap" parameterType="java.lang.Long">
		select <include refid="allFields" />
		 from ${table}
		 where id = ${"#{"}id}
	</select>

	<!-- 列表总数-->
	<select id="query" resultType="java.lang.Long"  parameterType="Object">
		select id from ${table}
		<include refid="whereSql"></include>
	</select>
	<!-- 分页查询 -->
	<select id="queryForList" parameterType="Object" resultMap="resultMap">
		select <include refid="allFields"></include> 
		from ${table}
		<include refid="whereSql"></include>
	</select>
	
	<delete id="deleteAllByPrimaryKey" parameterType="Object">
		delete from ${table}
		where id in
		<foreach item="id" collection="list" open="(" close=")" separator=",">
             ${"#{"}id}
        </foreach>
	</delete>

	<update id="updateByPrimaryKey" parameterType="Object">
		update ${table}
		<trim prefix="set" suffixOverrides=",">
<#if model_column?exists>
	<#list model_column as model>
		<#if (model.columnType = 'VARCHAR' || model.columnType = 'TEXT')>
			<if test="${model.changeColumnName?uncap_first} != null and ${model.changeColumnName?uncap_first} != ''">
				${model.columnName} = ${"#{"}${model.changeColumnName?uncap_first}},
			</if>
		<#else>
			<if test="${model.changeColumnName?uncap_first} != null">
				${model.columnName} = ${"#{"}${model.changeColumnName?uncap_first}},
			</if>
		</#if>
	</#list>
</#if>
			
			<if test="enable != null">
				<![CDATA[ enable = ${"#{"}enable},]]>
			</if>
			
			<if test="remark != null and remark != '' ">
				<![CDATA[ remark = ${"#{"}remark},]]>
			</if>
			
			<if test="updateBy != null and updateBy != '' ">
				<![CDATA[ update_by = ${"#{"}updateBy},]]>
			</if>
			
			<if test="updateTime != null">
				<![CDATA[ update_time = ${"#{"}updateTime},]]>
			</if>
		</trim>
		where id = ${"#{"}id}
	</update>
	
	<update id="updateAllByPrimaryKey" parameterType="Object">
		update ${table}
		<trim prefix="set" suffixOverrides=",">
		    <if test="enable != null " >
		  	<![CDATA[  enable = ${"#{"}enable}, ]]>
		    </if>
		    <if test="updateBy != null and updateBy != '' " >
		  	<![CDATA[  update_by = ${"#{"}updateBy}, ]]>
		    </if>
		    <if test="updateTime != null " >
		  	<![CDATA[  update_time = ${"#{"}updateTime}, ]]>
		    </if>
		</trim>
	    where id in
		<foreach item="id" index="index" collection="ids" open="(" separator="," close=")">
		    ${"#{"}id }
		</foreach>
	</update>
	
	<sql id="whereSql">
		<where>
			1=1
			<if test="id != null">
				and id = ${"#{"}id}
			</if>
<#if model_column?exists>
	<#list model_column as model>
		<#if (model.columnType = 'VARCHAR' || model.columnType = 'TEXT')>
			<if test="${model.changeColumnName?uncap_first} != null and ${model.changeColumnName?uncap_first} != ''">
				and ${model.columnName} = ${"#{"}${model.changeColumnName?uncap_first}}
			</if>
		<#else>
			<if test="${model.changeColumnName?uncap_first} != null">
				and ${model.columnName} = ${"#{"}${model.changeColumnName?uncap_first}}
			</if>
		</#if>
	</#list>
</#if>
			<if test="enable != null">
				and enable = ${"#{"}enable}
			</if>
		</where>
	</sql>
</mapper>