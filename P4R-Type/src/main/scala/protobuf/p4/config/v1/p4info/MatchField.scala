// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package p4.config.v1.p4info

/** @param annotationLocations
  *   Optional. If present, the location of `annotations[i]` is given by
  *   `annotation_locations[i]`.
  * @param doc
  *   Documentation of the match field
  * @param typeName
  *   unset if not user-defined type
  */
@SerialVersionUID(0L)
final case class MatchField(
    id: _root_.scala.Int = 0,
    name: _root_.scala.Predef.String = "",
    annotations: _root_.scala.Seq[_root_.scala.Predef.String] = _root_.scala.Seq.empty,
    annotationLocations: _root_.scala.Seq[p4.config.v1.p4types.SourceLocation] = _root_.scala.Seq.empty,
    bitwidth: _root_.scala.Int = 0,
    `match`: p4.config.v1.p4info.MatchField.Match = p4.config.v1.p4info.MatchField.Match.Empty,
    doc: _root_.scala.Option[p4.config.v1.p4info.Documentation] = _root_.scala.None,
    typeName: _root_.scala.Option[p4.config.v1.p4types.P4NamedType] = _root_.scala.None,
    structuredAnnotations: _root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation] = _root_.scala.Seq.empty,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[MatchField] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = id
        if (__value != 0) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeUInt32Size(1, __value)
        }
      };
      
      {
        val __value = name
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(2, __value)
        }
      };
      annotations.foreach { __item =>
        val __value = __item
        __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(3, __value)
      }
      annotationLocations.foreach { __item =>
        val __value = __item
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }
      
      {
        val __value = bitwidth
        if (__value != 0) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeInt32Size(4, __value)
        }
      };
      if (`match`.matchType.isDefined) {
        val __value = `match`.matchType.get.value
        __size += _root_.com.google.protobuf.CodedOutputStream.computeEnumSize(5, __value)
      };
      if (`match`.otherMatchType.isDefined) {
        val __value = `match`.otherMatchType.get
        __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(7, __value)
      };
      if (doc.isDefined) {
        val __value = doc.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (typeName.isDefined) {
        val __value = typeName.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      structuredAnnotations.foreach { __item =>
        val __value = __item
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }
      __size += unknownFields.serializedSize
      __size
    }
    override def serializedSize: _root_.scala.Int = {
      var __size = __serializedSizeMemoized
      if (__size == 0) {
        __size = __computeSerializedSize() + 1
        __serializedSizeMemoized = __size
      }
      __size - 1
      
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
      {
        val __v = id
        if (__v != 0) {
          _output__.writeUInt32(1, __v)
        }
      };
      {
        val __v = name
        if (!__v.isEmpty) {
          _output__.writeString(2, __v)
        }
      };
      annotations.foreach { __v =>
        val __m = __v
        _output__.writeString(3, __m)
      };
      {
        val __v = bitwidth
        if (__v != 0) {
          _output__.writeInt32(4, __v)
        }
      };
      `match`.matchType.foreach { __v =>
        val __m = __v.value
        _output__.writeEnum(5, __m)
      };
      doc.foreach { __v =>
        val __m = __v
        _output__.writeTag(6, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      `match`.otherMatchType.foreach { __v =>
        val __m = __v
        _output__.writeString(7, __m)
      };
      typeName.foreach { __v =>
        val __m = __v
        _output__.writeTag(8, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      structuredAnnotations.foreach { __v =>
        val __m = __v
        _output__.writeTag(9, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      annotationLocations.foreach { __v =>
        val __m = __v
        _output__.writeTag(10, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      unknownFields.writeTo(_output__)
    }
    def withId(__v: _root_.scala.Int): MatchField = copy(id = __v)
    def withName(__v: _root_.scala.Predef.String): MatchField = copy(name = __v)
    def clearAnnotations = copy(annotations = _root_.scala.Seq.empty)
    def addAnnotations(__vs: _root_.scala.Predef.String *): MatchField = addAllAnnotations(__vs)
    def addAllAnnotations(__vs: Iterable[_root_.scala.Predef.String]): MatchField = copy(annotations = annotations ++ __vs)
    def withAnnotations(__v: _root_.scala.Seq[_root_.scala.Predef.String]): MatchField = copy(annotations = __v)
    def clearAnnotationLocations = copy(annotationLocations = _root_.scala.Seq.empty)
    def addAnnotationLocations(__vs: p4.config.v1.p4types.SourceLocation *): MatchField = addAllAnnotationLocations(__vs)
    def addAllAnnotationLocations(__vs: Iterable[p4.config.v1.p4types.SourceLocation]): MatchField = copy(annotationLocations = annotationLocations ++ __vs)
    def withAnnotationLocations(__v: _root_.scala.Seq[p4.config.v1.p4types.SourceLocation]): MatchField = copy(annotationLocations = __v)
    def withBitwidth(__v: _root_.scala.Int): MatchField = copy(bitwidth = __v)
    def getMatchType: p4.config.v1.p4info.MatchField.MatchType = `match`.matchType.getOrElse(p4.config.v1.p4info.MatchField.MatchType.UNSPECIFIED)
    def withMatchType(__v: p4.config.v1.p4info.MatchField.MatchType): MatchField = copy(`match` = p4.config.v1.p4info.MatchField.Match.MatchType(__v))
    def getOtherMatchType: _root_.scala.Predef.String = `match`.otherMatchType.getOrElse("")
    def withOtherMatchType(__v: _root_.scala.Predef.String): MatchField = copy(`match` = p4.config.v1.p4info.MatchField.Match.OtherMatchType(__v))
    def getDoc: p4.config.v1.p4info.Documentation = doc.getOrElse(p4.config.v1.p4info.Documentation.defaultInstance)
    def clearDoc: MatchField = copy(doc = _root_.scala.None)
    def withDoc(__v: p4.config.v1.p4info.Documentation): MatchField = copy(doc = Option(__v))
    def getTypeName: p4.config.v1.p4types.P4NamedType = typeName.getOrElse(p4.config.v1.p4types.P4NamedType.defaultInstance)
    def clearTypeName: MatchField = copy(typeName = _root_.scala.None)
    def withTypeName(__v: p4.config.v1.p4types.P4NamedType): MatchField = copy(typeName = Option(__v))
    def clearStructuredAnnotations = copy(structuredAnnotations = _root_.scala.Seq.empty)
    def addStructuredAnnotations(__vs: p4.config.v1.p4types.StructuredAnnotation *): MatchField = addAllStructuredAnnotations(__vs)
    def addAllStructuredAnnotations(__vs: Iterable[p4.config.v1.p4types.StructuredAnnotation]): MatchField = copy(structuredAnnotations = structuredAnnotations ++ __vs)
    def withStructuredAnnotations(__v: _root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation]): MatchField = copy(structuredAnnotations = __v)
    def clearMatch: MatchField = copy(`match` = p4.config.v1.p4info.MatchField.Match.Empty)
    def withMatch(__v: p4.config.v1.p4info.MatchField.Match): MatchField = copy(`match` = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = id
          if (__t != 0) __t else null
        }
        case 2 => {
          val __t = name
          if (__t != "") __t else null
        }
        case 3 => annotations
        case 10 => annotationLocations
        case 4 => {
          val __t = bitwidth
          if (__t != 0) __t else null
        }
        case 5 => `match`.matchType.map(_.javaValueDescriptor).orNull
        case 7 => `match`.otherMatchType.orNull
        case 6 => doc.orNull
        case 8 => typeName.orNull
        case 9 => structuredAnnotations
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PInt(id)
        case 2 => _root_.scalapb.descriptors.PString(name)
        case 3 => _root_.scalapb.descriptors.PRepeated(annotations.iterator.map(_root_.scalapb.descriptors.PString(_)).toVector)
        case 10 => _root_.scalapb.descriptors.PRepeated(annotationLocations.iterator.map(_.toPMessage).toVector)
        case 4 => _root_.scalapb.descriptors.PInt(bitwidth)
        case 5 => `match`.matchType.map(__e => _root_.scalapb.descriptors.PEnum(__e.scalaValueDescriptor)).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 7 => `match`.otherMatchType.map(_root_.scalapb.descriptors.PString(_)).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 6 => doc.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 8 => typeName.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 9 => _root_.scalapb.descriptors.PRepeated(structuredAnnotations.iterator.map(_.toPMessage).toVector)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: p4.config.v1.p4info.MatchField.type = p4.config.v1.p4info.MatchField
    // @@protoc_insertion_point(GeneratedMessage[p4.config.v1.MatchField])
}

object MatchField extends scalapb.GeneratedMessageCompanion[p4.config.v1.p4info.MatchField] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[p4.config.v1.p4info.MatchField] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): p4.config.v1.p4info.MatchField = {
    var __id: _root_.scala.Int = 0
    var __name: _root_.scala.Predef.String = ""
    val __annotations: _root_.scala.collection.immutable.VectorBuilder[_root_.scala.Predef.String] = new _root_.scala.collection.immutable.VectorBuilder[_root_.scala.Predef.String]
    val __annotationLocations: _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.SourceLocation] = new _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.SourceLocation]
    var __bitwidth: _root_.scala.Int = 0
    var __doc: _root_.scala.Option[p4.config.v1.p4info.Documentation] = _root_.scala.None
    var __typeName: _root_.scala.Option[p4.config.v1.p4types.P4NamedType] = _root_.scala.None
    val __structuredAnnotations: _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.StructuredAnnotation] = new _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.StructuredAnnotation]
    var __match: p4.config.v1.p4info.MatchField.Match = p4.config.v1.p4info.MatchField.Match.Empty
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 8 =>
          __id = _input__.readUInt32()
        case 18 =>
          __name = _input__.readStringRequireUtf8()
        case 26 =>
          __annotations += _input__.readStringRequireUtf8()
        case 82 =>
          __annotationLocations += _root_.scalapb.LiteParser.readMessage[p4.config.v1.p4types.SourceLocation](_input__)
        case 32 =>
          __bitwidth = _input__.readInt32()
        case 40 =>
          __match = p4.config.v1.p4info.MatchField.Match.MatchType(p4.config.v1.p4info.MatchField.MatchType.fromValue(_input__.readEnum()))
        case 58 =>
          __match = p4.config.v1.p4info.MatchField.Match.OtherMatchType(_input__.readStringRequireUtf8())
        case 50 =>
          __doc = Option(__doc.fold(_root_.scalapb.LiteParser.readMessage[p4.config.v1.p4info.Documentation](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 66 =>
          __typeName = Option(__typeName.fold(_root_.scalapb.LiteParser.readMessage[p4.config.v1.p4types.P4NamedType](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 74 =>
          __structuredAnnotations += _root_.scalapb.LiteParser.readMessage[p4.config.v1.p4types.StructuredAnnotation](_input__)
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    p4.config.v1.p4info.MatchField(
        id = __id,
        name = __name,
        annotations = __annotations.result(),
        annotationLocations = __annotationLocations.result(),
        bitwidth = __bitwidth,
        doc = __doc,
        typeName = __typeName,
        structuredAnnotations = __structuredAnnotations.result(),
        `match` = __match,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[p4.config.v1.p4info.MatchField] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      p4.config.v1.p4info.MatchField(
        id = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Int]).getOrElse(0),
        name = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
        annotations = __fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).map(_.as[_root_.scala.Seq[_root_.scala.Predef.String]]).getOrElse(_root_.scala.Seq.empty),
        annotationLocations = __fieldsMap.get(scalaDescriptor.findFieldByNumber(10).get).map(_.as[_root_.scala.Seq[p4.config.v1.p4types.SourceLocation]]).getOrElse(_root_.scala.Seq.empty),
        bitwidth = __fieldsMap.get(scalaDescriptor.findFieldByNumber(4).get).map(_.as[_root_.scala.Int]).getOrElse(0),
        doc = __fieldsMap.get(scalaDescriptor.findFieldByNumber(6).get).flatMap(_.as[_root_.scala.Option[p4.config.v1.p4info.Documentation]]),
        typeName = __fieldsMap.get(scalaDescriptor.findFieldByNumber(8).get).flatMap(_.as[_root_.scala.Option[p4.config.v1.p4types.P4NamedType]]),
        structuredAnnotations = __fieldsMap.get(scalaDescriptor.findFieldByNumber(9).get).map(_.as[_root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation]]).getOrElse(_root_.scala.Seq.empty),
        `match` = __fieldsMap.get(scalaDescriptor.findFieldByNumber(5).get).flatMap(_.as[_root_.scala.Option[_root_.scalapb.descriptors.EnumValueDescriptor]]).map(__e => p4.config.v1.p4info.MatchField.Match.MatchType(p4.config.v1.p4info.MatchField.MatchType.fromValue(__e.number)))
            .orElse[p4.config.v1.p4info.MatchField.Match](__fieldsMap.get(scalaDescriptor.findFieldByNumber(7).get).flatMap(_.as[_root_.scala.Option[_root_.scala.Predef.String]]).map(p4.config.v1.p4info.MatchField.Match.OtherMatchType(_)))
            .getOrElse(p4.config.v1.p4info.MatchField.Match.Empty)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = P4InfoProto.javaDescriptor.getMessageTypes().get(7)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = P4InfoProto.scalaDescriptor.messages(7)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 10 => __out = p4.config.v1.p4types.SourceLocation
      case 6 => __out = p4.config.v1.p4info.Documentation
      case 8 => __out = p4.config.v1.p4types.P4NamedType
      case 9 => __out = p4.config.v1.p4types.StructuredAnnotation
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = {
    (__fieldNumber: @_root_.scala.unchecked) match {
      case 5 => p4.config.v1.p4info.MatchField.MatchType
    }
  }
  lazy val defaultInstance = p4.config.v1.p4info.MatchField(
    id = 0,
    name = "",
    annotations = _root_.scala.Seq.empty,
    annotationLocations = _root_.scala.Seq.empty,
    bitwidth = 0,
    doc = _root_.scala.None,
    typeName = _root_.scala.None,
    structuredAnnotations = _root_.scala.Seq.empty,
    `match` = p4.config.v1.p4info.MatchField.Match.Empty
  )
  sealed abstract class MatchType(val value: _root_.scala.Int) extends _root_.scalapb.GeneratedEnum {
    type EnumType = MatchType
    def isUnspecified: _root_.scala.Boolean = false
    def isExact: _root_.scala.Boolean = false
    def isLpm: _root_.scala.Boolean = false
    def isTernary: _root_.scala.Boolean = false
    def isRange: _root_.scala.Boolean = false
    def isOptional: _root_.scala.Boolean = false
    def companion: _root_.scalapb.GeneratedEnumCompanion[MatchType] = p4.config.v1.p4info.MatchField.MatchType
    final def asRecognized: _root_.scala.Option[p4.config.v1.p4info.MatchField.MatchType.Recognized] = if (isUnrecognized) _root_.scala.None else _root_.scala.Some(this.asInstanceOf[p4.config.v1.p4info.MatchField.MatchType.Recognized])
  }
  
  object MatchType extends _root_.scalapb.GeneratedEnumCompanion[MatchType] {
    sealed trait Recognized extends MatchType
    implicit def enumCompanion: _root_.scalapb.GeneratedEnumCompanion[MatchType] = this
    
    @SerialVersionUID(0L)
    case object UNSPECIFIED extends MatchType(0) with MatchType.Recognized {
      val index = 0
      val name = "UNSPECIFIED"
      override def isUnspecified: _root_.scala.Boolean = true
    }
    
    @SerialVersionUID(0L)
    case object EXACT extends MatchType(2) with MatchType.Recognized {
      val index = 1
      val name = "EXACT"
      override def isExact: _root_.scala.Boolean = true
    }
    
    @SerialVersionUID(0L)
    case object LPM extends MatchType(3) with MatchType.Recognized {
      val index = 2
      val name = "LPM"
      override def isLpm: _root_.scala.Boolean = true
    }
    
    @SerialVersionUID(0L)
    case object TERNARY extends MatchType(4) with MatchType.Recognized {
      val index = 3
      val name = "TERNARY"
      override def isTernary: _root_.scala.Boolean = true
    }
    
    @SerialVersionUID(0L)
    case object RANGE extends MatchType(5) with MatchType.Recognized {
      val index = 4
      val name = "RANGE"
      override def isRange: _root_.scala.Boolean = true
    }
    
    @SerialVersionUID(0L)
    case object OPTIONAL extends MatchType(6) with MatchType.Recognized {
      val index = 5
      val name = "OPTIONAL"
      override def isOptional: _root_.scala.Boolean = true
    }
    
    @SerialVersionUID(0L)
    final case class Unrecognized(unrecognizedValue: _root_.scala.Int) extends MatchType(unrecognizedValue) with _root_.scalapb.UnrecognizedEnum
    lazy val values = scala.collection.immutable.Seq(UNSPECIFIED, EXACT, LPM, TERNARY, RANGE, OPTIONAL)
    def fromValue(__value: _root_.scala.Int): MatchType = __value match {
      case 0 => UNSPECIFIED
      case 2 => EXACT
      case 3 => LPM
      case 4 => TERNARY
      case 5 => RANGE
      case 6 => OPTIONAL
      case __other => Unrecognized(__other)
    }
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.EnumDescriptor = p4.config.v1.p4info.MatchField.javaDescriptor.getEnumTypes().get(0)
    def scalaDescriptor: _root_.scalapb.descriptors.EnumDescriptor = p4.config.v1.p4info.MatchField.scalaDescriptor.enums(0)
  }
  sealed trait Match extends _root_.scalapb.GeneratedOneof {
    def isEmpty: _root_.scala.Boolean = false
    def isDefined: _root_.scala.Boolean = true
    def isMatchType: _root_.scala.Boolean = false
    def isOtherMatchType: _root_.scala.Boolean = false
    def matchType: _root_.scala.Option[p4.config.v1.p4info.MatchField.MatchType] = _root_.scala.None
    def otherMatchType: _root_.scala.Option[_root_.scala.Predef.String] = _root_.scala.None
  }
  object Match {
    @SerialVersionUID(0L)
    case object Empty extends p4.config.v1.p4info.MatchField.Match {
      type ValueType = _root_.scala.Nothing
      override def isEmpty: _root_.scala.Boolean = true
      override def isDefined: _root_.scala.Boolean = false
      override def number: _root_.scala.Int = 0
      override def value: _root_.scala.Nothing = throw new java.util.NoSuchElementException("Empty.value")
    }
  
    @SerialVersionUID(0L)
    final case class MatchType(value: p4.config.v1.p4info.MatchField.MatchType) extends p4.config.v1.p4info.MatchField.Match {
      type ValueType = p4.config.v1.p4info.MatchField.MatchType
      override def isMatchType: _root_.scala.Boolean = true
      override def matchType: _root_.scala.Option[p4.config.v1.p4info.MatchField.MatchType] = Some(value)
      override def number: _root_.scala.Int = 5
    }
    @SerialVersionUID(0L)
    final case class OtherMatchType(value: _root_.scala.Predef.String) extends p4.config.v1.p4info.MatchField.Match {
      type ValueType = _root_.scala.Predef.String
      override def isOtherMatchType: _root_.scala.Boolean = true
      override def otherMatchType: _root_.scala.Option[_root_.scala.Predef.String] = Some(value)
      override def number: _root_.scala.Int = 7
    }
  }
  implicit class MatchFieldLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4info.MatchField]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, p4.config.v1.p4info.MatchField](_l) {
    def id: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Int] = field(_.id)((c_, f_) => c_.copy(id = f_))
    def name: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.name)((c_, f_) => c_.copy(name = f_))
    def annotations: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[_root_.scala.Predef.String]] = field(_.annotations)((c_, f_) => c_.copy(annotations = f_))
    def annotationLocations: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[p4.config.v1.p4types.SourceLocation]] = field(_.annotationLocations)((c_, f_) => c_.copy(annotationLocations = f_))
    def bitwidth: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Int] = field(_.bitwidth)((c_, f_) => c_.copy(bitwidth = f_))
    def matchType: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4info.MatchField.MatchType] = field(_.getMatchType)((c_, f_) => c_.copy(`match` = p4.config.v1.p4info.MatchField.Match.MatchType(f_)))
    def otherMatchType: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.getOtherMatchType)((c_, f_) => c_.copy(`match` = p4.config.v1.p4info.MatchField.Match.OtherMatchType(f_)))
    def doc: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4info.Documentation] = field(_.getDoc)((c_, f_) => c_.copy(doc = Option(f_)))
    def optionalDoc: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[p4.config.v1.p4info.Documentation]] = field(_.doc)((c_, f_) => c_.copy(doc = f_))
    def typeName: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4types.P4NamedType] = field(_.getTypeName)((c_, f_) => c_.copy(typeName = Option(f_)))
    def optionalTypeName: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[p4.config.v1.p4types.P4NamedType]] = field(_.typeName)((c_, f_) => c_.copy(typeName = f_))
    def structuredAnnotations: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation]] = field(_.structuredAnnotations)((c_, f_) => c_.copy(structuredAnnotations = f_))
    def `match`: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4info.MatchField.Match] = field(_.`match`)((c_, f_) => c_.copy(`match` = f_))
  }
  final val ID_FIELD_NUMBER = 1
  final val NAME_FIELD_NUMBER = 2
  final val ANNOTATIONS_FIELD_NUMBER = 3
  final val ANNOTATION_LOCATIONS_FIELD_NUMBER = 10
  final val BITWIDTH_FIELD_NUMBER = 4
  final val MATCH_TYPE_FIELD_NUMBER = 5
  final val OTHER_MATCH_TYPE_FIELD_NUMBER = 7
  final val DOC_FIELD_NUMBER = 6
  final val TYPE_NAME_FIELD_NUMBER = 8
  final val STRUCTURED_ANNOTATIONS_FIELD_NUMBER = 9
  def of(
    id: _root_.scala.Int,
    name: _root_.scala.Predef.String,
    annotations: _root_.scala.Seq[_root_.scala.Predef.String],
    annotationLocations: _root_.scala.Seq[p4.config.v1.p4types.SourceLocation],
    bitwidth: _root_.scala.Int,
    `match`: p4.config.v1.p4info.MatchField.Match,
    doc: _root_.scala.Option[p4.config.v1.p4info.Documentation],
    typeName: _root_.scala.Option[p4.config.v1.p4types.P4NamedType],
    structuredAnnotations: _root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation]
  ): _root_.p4.config.v1.p4info.MatchField = _root_.p4.config.v1.p4info.MatchField(
    id,
    name,
    annotations,
    annotationLocations,
    bitwidth,
    `match`,
    doc,
    typeName,
    structuredAnnotations
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[p4.config.v1.MatchField])
}
