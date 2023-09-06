// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package p4.config.v1.p4types

/** @param annotationLocations
  *   Optional. If present, the location of `annotations[i]` is given by
  *   `annotation_locations[i]`.
  */
@SerialVersionUID(0L)
final case class P4HeaderTypeSpec(
    members: _root_.scala.Seq[p4.config.v1.p4types.P4HeaderTypeSpec.Member] = _root_.scala.Seq.empty,
    annotations: _root_.scala.Seq[_root_.scala.Predef.String] = _root_.scala.Seq.empty,
    annotationLocations: _root_.scala.Seq[p4.config.v1.p4types.SourceLocation] = _root_.scala.Seq.empty,
    structuredAnnotations: _root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation] = _root_.scala.Seq.empty,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[P4HeaderTypeSpec] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      members.foreach { __item =>
        val __value = __item
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }
      annotations.foreach { __item =>
        val __value = __item
        __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(2, __value)
      }
      annotationLocations.foreach { __item =>
        val __value = __item
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }
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
      members.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      annotations.foreach { __v =>
        val __m = __v
        _output__.writeString(2, __m)
      };
      annotationLocations.foreach { __v =>
        val __m = __v
        _output__.writeTag(3, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      structuredAnnotations.foreach { __v =>
        val __m = __v
        _output__.writeTag(4, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      unknownFields.writeTo(_output__)
    }
    def clearMembers = copy(members = _root_.scala.Seq.empty)
    def addMembers(__vs: p4.config.v1.p4types.P4HeaderTypeSpec.Member *): P4HeaderTypeSpec = addAllMembers(__vs)
    def addAllMembers(__vs: Iterable[p4.config.v1.p4types.P4HeaderTypeSpec.Member]): P4HeaderTypeSpec = copy(members = members ++ __vs)
    def withMembers(__v: _root_.scala.Seq[p4.config.v1.p4types.P4HeaderTypeSpec.Member]): P4HeaderTypeSpec = copy(members = __v)
    def clearAnnotations = copy(annotations = _root_.scala.Seq.empty)
    def addAnnotations(__vs: _root_.scala.Predef.String *): P4HeaderTypeSpec = addAllAnnotations(__vs)
    def addAllAnnotations(__vs: Iterable[_root_.scala.Predef.String]): P4HeaderTypeSpec = copy(annotations = annotations ++ __vs)
    def withAnnotations(__v: _root_.scala.Seq[_root_.scala.Predef.String]): P4HeaderTypeSpec = copy(annotations = __v)
    def clearAnnotationLocations = copy(annotationLocations = _root_.scala.Seq.empty)
    def addAnnotationLocations(__vs: p4.config.v1.p4types.SourceLocation *): P4HeaderTypeSpec = addAllAnnotationLocations(__vs)
    def addAllAnnotationLocations(__vs: Iterable[p4.config.v1.p4types.SourceLocation]): P4HeaderTypeSpec = copy(annotationLocations = annotationLocations ++ __vs)
    def withAnnotationLocations(__v: _root_.scala.Seq[p4.config.v1.p4types.SourceLocation]): P4HeaderTypeSpec = copy(annotationLocations = __v)
    def clearStructuredAnnotations = copy(structuredAnnotations = _root_.scala.Seq.empty)
    def addStructuredAnnotations(__vs: p4.config.v1.p4types.StructuredAnnotation *): P4HeaderTypeSpec = addAllStructuredAnnotations(__vs)
    def addAllStructuredAnnotations(__vs: Iterable[p4.config.v1.p4types.StructuredAnnotation]): P4HeaderTypeSpec = copy(structuredAnnotations = structuredAnnotations ++ __vs)
    def withStructuredAnnotations(__v: _root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation]): P4HeaderTypeSpec = copy(structuredAnnotations = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => members
        case 2 => annotations
        case 3 => annotationLocations
        case 4 => structuredAnnotations
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PRepeated(members.iterator.map(_.toPMessage).toVector)
        case 2 => _root_.scalapb.descriptors.PRepeated(annotations.iterator.map(_root_.scalapb.descriptors.PString(_)).toVector)
        case 3 => _root_.scalapb.descriptors.PRepeated(annotationLocations.iterator.map(_.toPMessage).toVector)
        case 4 => _root_.scalapb.descriptors.PRepeated(structuredAnnotations.iterator.map(_.toPMessage).toVector)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: p4.config.v1.p4types.P4HeaderTypeSpec.type = p4.config.v1.p4types.P4HeaderTypeSpec
    // @@protoc_insertion_point(GeneratedMessage[p4.config.v1.P4HeaderTypeSpec])
}

object P4HeaderTypeSpec extends scalapb.GeneratedMessageCompanion[p4.config.v1.p4types.P4HeaderTypeSpec] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[p4.config.v1.p4types.P4HeaderTypeSpec] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): p4.config.v1.p4types.P4HeaderTypeSpec = {
    val __members: _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.P4HeaderTypeSpec.Member] = new _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.P4HeaderTypeSpec.Member]
    val __annotations: _root_.scala.collection.immutable.VectorBuilder[_root_.scala.Predef.String] = new _root_.scala.collection.immutable.VectorBuilder[_root_.scala.Predef.String]
    val __annotationLocations: _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.SourceLocation] = new _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.SourceLocation]
    val __structuredAnnotations: _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.StructuredAnnotation] = new _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.StructuredAnnotation]
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __members += _root_.scalapb.LiteParser.readMessage[p4.config.v1.p4types.P4HeaderTypeSpec.Member](_input__)
        case 18 =>
          __annotations += _input__.readStringRequireUtf8()
        case 26 =>
          __annotationLocations += _root_.scalapb.LiteParser.readMessage[p4.config.v1.p4types.SourceLocation](_input__)
        case 34 =>
          __structuredAnnotations += _root_.scalapb.LiteParser.readMessage[p4.config.v1.p4types.StructuredAnnotation](_input__)
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    p4.config.v1.p4types.P4HeaderTypeSpec(
        members = __members.result(),
        annotations = __annotations.result(),
        annotationLocations = __annotationLocations.result(),
        structuredAnnotations = __structuredAnnotations.result(),
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[p4.config.v1.p4types.P4HeaderTypeSpec] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      p4.config.v1.p4types.P4HeaderTypeSpec(
        members = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Seq[p4.config.v1.p4types.P4HeaderTypeSpec.Member]]).getOrElse(_root_.scala.Seq.empty),
        annotations = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Seq[_root_.scala.Predef.String]]).getOrElse(_root_.scala.Seq.empty),
        annotationLocations = __fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).map(_.as[_root_.scala.Seq[p4.config.v1.p4types.SourceLocation]]).getOrElse(_root_.scala.Seq.empty),
        structuredAnnotations = __fieldsMap.get(scalaDescriptor.findFieldByNumber(4).get).map(_.as[_root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation]]).getOrElse(_root_.scala.Seq.empty)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = P4TypesProto.javaDescriptor.getMessageTypes().get(11)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = P4TypesProto.scalaDescriptor.messages(11)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = p4.config.v1.p4types.P4HeaderTypeSpec.Member
      case 3 => __out = p4.config.v1.p4types.SourceLocation
      case 4 => __out = p4.config.v1.p4types.StructuredAnnotation
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] =
    Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]](
      _root_.p4.config.v1.p4types.P4HeaderTypeSpec.Member
    )
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = p4.config.v1.p4types.P4HeaderTypeSpec(
    members = _root_.scala.Seq.empty,
    annotations = _root_.scala.Seq.empty,
    annotationLocations = _root_.scala.Seq.empty,
    structuredAnnotations = _root_.scala.Seq.empty
  )
  @SerialVersionUID(0L)
  final case class Member(
      name: _root_.scala.Predef.String = "",
      typeSpec: _root_.scala.Option[p4.config.v1.p4types.P4BitstringLikeTypeSpec] = _root_.scala.None,
      unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
      ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[Member] {
      @transient
      private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
      private[this] def __computeSerializedSize(): _root_.scala.Int = {
        var __size = 0
        
        {
          val __value = name
          if (!__value.isEmpty) {
            __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(1, __value)
          }
        };
        if (typeSpec.isDefined) {
          val __value = typeSpec.get
          __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
        };
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
          val __v = name
          if (!__v.isEmpty) {
            _output__.writeString(1, __v)
          }
        };
        typeSpec.foreach { __v =>
          val __m = __v
          _output__.writeTag(2, 2)
          _output__.writeUInt32NoTag(__m.serializedSize)
          __m.writeTo(_output__)
        };
        unknownFields.writeTo(_output__)
      }
      def withName(__v: _root_.scala.Predef.String): Member = copy(name = __v)
      def getTypeSpec: p4.config.v1.p4types.P4BitstringLikeTypeSpec = typeSpec.getOrElse(p4.config.v1.p4types.P4BitstringLikeTypeSpec.defaultInstance)
      def clearTypeSpec: Member = copy(typeSpec = _root_.scala.None)
      def withTypeSpec(__v: p4.config.v1.p4types.P4BitstringLikeTypeSpec): Member = copy(typeSpec = Option(__v))
      def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
      def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
      def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
        (__fieldNumber: @_root_.scala.unchecked) match {
          case 1 => {
            val __t = name
            if (__t != "") __t else null
          }
          case 2 => typeSpec.orNull
        }
      }
      def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
        _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
        (__field.number: @_root_.scala.unchecked) match {
          case 1 => _root_.scalapb.descriptors.PString(name)
          case 2 => typeSpec.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        }
      }
      def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
      def companion: p4.config.v1.p4types.P4HeaderTypeSpec.Member.type = p4.config.v1.p4types.P4HeaderTypeSpec.Member
      // @@protoc_insertion_point(GeneratedMessage[p4.config.v1.P4HeaderTypeSpec.Member])
  }
  
  object Member extends scalapb.GeneratedMessageCompanion[p4.config.v1.p4types.P4HeaderTypeSpec.Member] {
    implicit def messageCompanion: scalapb.GeneratedMessageCompanion[p4.config.v1.p4types.P4HeaderTypeSpec.Member] = this
    def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): p4.config.v1.p4types.P4HeaderTypeSpec.Member = {
      var __name: _root_.scala.Predef.String = ""
      var __typeSpec: _root_.scala.Option[p4.config.v1.p4types.P4BitstringLikeTypeSpec] = _root_.scala.None
      var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __name = _input__.readStringRequireUtf8()
          case 18 =>
            __typeSpec = Option(__typeSpec.fold(_root_.scalapb.LiteParser.readMessage[p4.config.v1.p4types.P4BitstringLikeTypeSpec](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
          case tag =>
            if (_unknownFields__ == null) {
              _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
            }
            _unknownFields__.parseField(tag, _input__)
        }
      }
      p4.config.v1.p4types.P4HeaderTypeSpec.Member(
          name = __name,
          typeSpec = __typeSpec,
          unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
      )
    }
    implicit def messageReads: _root_.scalapb.descriptors.Reads[p4.config.v1.p4types.P4HeaderTypeSpec.Member] = _root_.scalapb.descriptors.Reads{
      case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
        _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
        p4.config.v1.p4types.P4HeaderTypeSpec.Member(
          name = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
          typeSpec = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).flatMap(_.as[_root_.scala.Option[p4.config.v1.p4types.P4BitstringLikeTypeSpec]])
        )
      case _ => throw new RuntimeException("Expected PMessage")
    }
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = p4.config.v1.p4types.P4HeaderTypeSpec.javaDescriptor.getNestedTypes().get(0)
    def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = p4.config.v1.p4types.P4HeaderTypeSpec.scalaDescriptor.nestedMessages(0)
    def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
      var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
      (__number: @_root_.scala.unchecked) match {
        case 2 => __out = p4.config.v1.p4types.P4BitstringLikeTypeSpec
      }
      __out
    }
    lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
    def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
    lazy val defaultInstance = p4.config.v1.p4types.P4HeaderTypeSpec.Member(
      name = "",
      typeSpec = _root_.scala.None
    )
    implicit class MemberLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4types.P4HeaderTypeSpec.Member]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, p4.config.v1.p4types.P4HeaderTypeSpec.Member](_l) {
      def name: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.name)((c_, f_) => c_.copy(name = f_))
      def typeSpec: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4types.P4BitstringLikeTypeSpec] = field(_.getTypeSpec)((c_, f_) => c_.copy(typeSpec = Option(f_)))
      def optionalTypeSpec: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[p4.config.v1.p4types.P4BitstringLikeTypeSpec]] = field(_.typeSpec)((c_, f_) => c_.copy(typeSpec = f_))
    }
    final val NAME_FIELD_NUMBER = 1
    final val TYPE_SPEC_FIELD_NUMBER = 2
    def of(
      name: _root_.scala.Predef.String,
      typeSpec: _root_.scala.Option[p4.config.v1.p4types.P4BitstringLikeTypeSpec]
    ): _root_.p4.config.v1.p4types.P4HeaderTypeSpec.Member = _root_.p4.config.v1.p4types.P4HeaderTypeSpec.Member(
      name,
      typeSpec
    )
    // @@protoc_insertion_point(GeneratedMessageCompanion[p4.config.v1.P4HeaderTypeSpec.Member])
  }
  
  implicit class P4HeaderTypeSpecLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4types.P4HeaderTypeSpec]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, p4.config.v1.p4types.P4HeaderTypeSpec](_l) {
    def members: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[p4.config.v1.p4types.P4HeaderTypeSpec.Member]] = field(_.members)((c_, f_) => c_.copy(members = f_))
    def annotations: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[_root_.scala.Predef.String]] = field(_.annotations)((c_, f_) => c_.copy(annotations = f_))
    def annotationLocations: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[p4.config.v1.p4types.SourceLocation]] = field(_.annotationLocations)((c_, f_) => c_.copy(annotationLocations = f_))
    def structuredAnnotations: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation]] = field(_.structuredAnnotations)((c_, f_) => c_.copy(structuredAnnotations = f_))
  }
  final val MEMBERS_FIELD_NUMBER = 1
  final val ANNOTATIONS_FIELD_NUMBER = 2
  final val ANNOTATION_LOCATIONS_FIELD_NUMBER = 3
  final val STRUCTURED_ANNOTATIONS_FIELD_NUMBER = 4
  def of(
    members: _root_.scala.Seq[p4.config.v1.p4types.P4HeaderTypeSpec.Member],
    annotations: _root_.scala.Seq[_root_.scala.Predef.String],
    annotationLocations: _root_.scala.Seq[p4.config.v1.p4types.SourceLocation],
    structuredAnnotations: _root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation]
  ): _root_.p4.config.v1.p4types.P4HeaderTypeSpec = _root_.p4.config.v1.p4types.P4HeaderTypeSpec(
    members,
    annotations,
    annotationLocations,
    structuredAnnotations
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[p4.config.v1.P4HeaderTypeSpec])
}