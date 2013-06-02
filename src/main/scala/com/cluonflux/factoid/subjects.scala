package com.cluonflux.factoid

/**
 * A subject about which factoids can be assembled by gathering assertions
 *
 * @param guid       primary key
 * @param properties properties that have been asserted about this subject
 * @tparam S         the type of subject
 */
case class Subject[S <: Subject[S]](guid: Guid[Subject[S]], properties: Seq[Factoid[_]])

/**
 * A type of subject.
 *
 * @param guid       primary key
 * @param parentGuid optional, guid of this subject type's supertype
 * @param name       name
 */
case class SubjectType(guid: Guid[SubjectType], parentGuid: Option[Guid[SubjectType]], name: String)

/**
 * A collection of assertions about some property of a subject.
 *
 * @param guid         primary key
 * @param subjectGuid  guid of the subject that this factoid asserts about
 * @param propertyGuid guid of the property that this factoid collects assertions of
 * @param assertions   assertions that this factoid asserts about the subject
 * @tparam T           type of value that all assertions assert
 */
case class Factoid[T](guid: Guid[Factoid[T]],
               subjectGuid: Guid[Subject[_]],
              propertyGuid: Guid[PropertyType],
                assertions: Seq[Assertion[_ <: T]])
