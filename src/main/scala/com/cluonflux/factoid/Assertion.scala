package com.cluonflux.factoid

import spray.json.JsValue


/**
 * A type of property that a [[com.cluonflux.factoid.Subject]] might have.
 *
 * @param guid               primary key
 * @param parentGuid         optional, guid of this property's supertype
 * @param dataType           type of data this property holds
 * @param typeInfo           extra information about the data type (e.g. an array of strings for an enum)
 */
case class PropertyType(guid: Guid[PropertyType],
                  parentGuid: Option[Guid[PropertyType]],
                    dataType: DataType,
                    typeInfo: Option[JsValue])


/**
 * A type of assertion that subjects of some type have a property of some type.
 *
 * TODO validation rules?
 *
 * TODO constrain to authority type too?
 *
 * @param guid             primary key
 * @param parentTypeGuid   optional, guid of this factoid's supertype
 * @param name             name of the type
 * @param propertyTypeGuid denotes the type of property that assertions of this type allege
 * @param subjectTypeGuid  denotes the type of subject that this factoid can make assertions about
 */
case class AssertionType(guid: Guid[AssertionType],
               parentTypeGuid: Option[Guid[AssertionType]],
                         name: String,
             propertyTypeGuid: Guid[PropertyType],
              subjectTypeGuid: Guid[SubjectType])

/**
 * An assertion that some subject has a property with a particular value. Taken collectively, all the assertions that connect one Subject with one Property
 * @param guid          primary key
 * @param typeGuid      assertion type
 * @param authorityGuid guid of the entity that has made the claim
 * @param confidence    confidence that the claimant has in this claim
 * @param value         value that the claimant asserts the subject has for this property
 * @tparam T            type of values
 */
case class Assertion[T](guid: Guid[Assertion[T]],
                    typeGuid: Guid[AssertionType],
                 subjectGuid: Guid[Subject],
                 factoidGuid: Guid[Factoid],
               authorityGuid: Guid[Authority],
               evidenceGuids: Seq[Guid[Evidence]],
                  confidence: Int,
                       value: T)

/**
 * Evidence offered by an [[com.cluonflux.factoid.Authority authority]] in support of one of its [[com.cluonflux.factoid.Assertion assertions]].
 * @param guid           primary key
 * @param authorityGuid  authority that produced/asserted
 * @param assertionGuids assertions whose credibility is claimed to depend on this evidence
 *
 */
case class Evidence(guid: Guid[Evidence], authorityGuid: Guid[Authority], assertionGuids: Seq[Guid[Assertion[_]]])

case class EvidenceUrl(guid: Guid[EvidenceUrl], url: String, urlType: EvidenceUrlType, parentGuid: Option[Guid[EvidenceUrl]], childGuids: Map[EvidenceUrlType, Seq[Guid[EvidenceUrl]]])



/**
 * An entity that makes assertions about subjects.
 *
 * @param guid            primary key
 * @param trustworthiness admin-asserted trustworthiness of this authority
 */
case class Authority(guid: Guid[Authority], trustworthiness: Int)