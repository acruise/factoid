package com.cluonflux.factoid

import spray.json.JsValue


/**
 * A type of property that a [[com.cluonflux.factoid.Subject]] might have.
 *
 * @param guid               primary key
 * @param parentGuid         optional, guid of this property's supertype
 * @param assertionTypeGuids guids of assertion types to which this property is applicable (TODO might need an intersect between property ← x → assertion, e.g. for required/optional)
 * @param dataType           type of data this property holds
 * @param typeInfo           extra information about the data type (e.g. an array of strings for an enum)
 */
case class PropertyType(guid: Guid[PropertyType],
                  parentGuid: Option[Guid[PropertyType]],
            assertionTypeGuids: Set[Guid[AssertionType]],
                    dataType: DataType,
                    typeInfo: Option[JsValue])


/**
 * A class of assertion
 * @param guid            primary key
 * @param name            name of the type
 * @param parentGuid      optional, guid of this factoid's supertype
 * @param subjectTypeGuid guid of the subject type that this factoid can make assertions about
 */
case class AssertionType(guid: Guid[AssertionType], name: String, parentGuid: Guid[AssertionType], subjectTypeGuid: Guid[SubjectType])

/**
 * An assertion that some subject has a property
 * @param guid          primary key
 * @param authorityGuid guid of the entity that has made the claim
 * @param confidence    confidence that the claimant has in this claim
 * @param value         value that the claimant asserts the subject has for this property
 * @tparam T            type of values
 */
case class Assertion[T](guid: Guid[Assertion[T]], subjectGuid: Guid[Subject], authorityGuid: Guid[Authority], confidence: Int, value: T)

/**
 * An entity that makes assertions about subjects.
 *
 * @param guid            primary key
 * @param trustworthiness admin-asserted trustworthiness of this authority
 */
case class Authority(guid: Guid[Authority], trustworthiness: Int)