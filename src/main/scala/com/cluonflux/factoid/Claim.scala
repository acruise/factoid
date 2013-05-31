package com.cluonflux.factoid

import spray.json.JsValue

/**
 * A type of property that a [[com.cluonflux.factoid.Subject]] might have.
 *
 * @param guid             primary key
 * @param parentGuid       optional, guid of this property's supertype
 * @param factoidTypeGuids guids of factoid types to which this property is applicable (TODO might need an intersect between property ← x → factoid, e.g. for required/optional)
 * @param dataType         type of data this property holds
 * @param typeInfo         extra information about the data type (e.g. an array of strings for an enum)
 */
case class Property(guid: Guid[Property],
              parentGuid: Option[Guid[Property]],
        factoidTypeGuids: Set[Guid[FactoidType]],
                dataType: DataType,
                typeInfo: Option[JsValue])

/**
 * @param guid         primary key
 * @param subjectGuid  guid of the subject that this factoid asserts about
 * @param propertyGuid guid of the property that this factoid collects assertions of
 * @param claims       claims that this factoid asserts about the subject
 * @tparam T           type of value that all claims assert
 */
case class Factoid[T](guid: Guid[Factoid[T]], subjectGuid: Guid[Subject[_]], propertyGuid: Guid[Property], claims: Seq[Claim[_ <: T]])

/**
 * A class of factoid
 * @param guid            primary key
 * @param name            name of the type
 * @param parentGuid      optional, guid of this factoid's supertype
 * @param subjectTypeGuid guid of the subject type that this factoid can make claims about
 */
case class FactoidType(guid: Guid[FactoidType], name: String, parentGuid: Guid[FactoidType], subjectTypeGuid: Guid[SubjectType])

/**
 * A claim that a subject has a property
 * @param guid         primary key
 * @param claimantGuid guid of the entity that has made the claim
 * @param confidence   confidence that the claimant has in this claim
 * @param claimedValue value that the claimant asserts the subject has for this property
 * @tparam T           type of values
 */
case class Claim[T](guid: Guid[Claim[T]], claimantGuid: Guid[Claimant], confidence: Int, claimedValue: T)

case class Claimant(guid: Guid[Claimant], trustworthiness: Int)

case class Subject[S <: Subject[S]](guid: Guid[Subject[S]], factoids: Seq[Factoid[_]])

case class SubjectType(guid: Guid[SubjectType], parentGuid: Option[Guid[SubjectType]], name: String)