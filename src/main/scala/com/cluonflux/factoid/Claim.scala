package com.cluonflux.factoid

import spray.json.JsValue

case class Property[T](guid: Guid[Property[T]],
                 parentGuid: Guid[Property[_]],
           factoidTypeGuids: Set[Guid[FactoidType]],
                   dataType: Class[T],
                   typeInfo: Option[JsValue])

case class FactoidType(guid: Guid[FactoidType], parentGuid: Guid[FactoidType], name: String)

case class Factoid[T](guid: Guid[Factoid[T]], subjectGuid: Guid[Subject[_]], propertyGuid: Guid[Property[_]], claims: Seq[Claim[_ <: T]])

case class Claim[T](guid: Guid[Claim[T]], claimantGuid: Guid[Claimant], confidence: Int, claimedValue: T)

case class Claimant(guid: Guid[Claimant], trustworthiness: Int)

case class Subject[S <: Subject[S]](guid: Guid[Subject[S]], factoids: Seq[Factoid[_]])