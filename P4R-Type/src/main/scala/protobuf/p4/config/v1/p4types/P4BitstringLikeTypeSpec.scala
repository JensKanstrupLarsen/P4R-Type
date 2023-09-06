// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package p4.config.v1.p4types

/** @param annotations
  *   Useful to identify well-known types, such as IP address or Ethernet MAC
  *   address.
  * @param annotationLocations
  *   Optional. If present, the location of `annotations[i]` is given by
  *   `annotation_locations[i]`.
  */
@SerialVersionUID(0L)
final case class P4BitstringLikeTypeSpec(
    typeSpec: p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec = p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Empty,
    annotations: _root_.scala.Seq[_root_.scala.Predef.String] = _root_.scala.Seq.empty,
    annotationLocations: _root_.scala.Seq[p4.config.v1.p4types.SourceLocation] = _root_.scala.Seq.empty,
    structuredAnnotations: _root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation] = _root_.scala.Seq.empty,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[P4BitstringLikeTypeSpec] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      if (typeSpec.bit.isDefined) {
        val __value = typeSpec.bit.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (typeSpec.int.isDefined) {
        val __value = typeSpec.int.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (typeSpec.varbit.isDefined) {
        val __value = typeSpec.varbit.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      annotations.foreach { __item =>
        val __value = __item
        __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(4, __value)
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
      typeSpec.bit.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      typeSpec.int.foreach { __v =>
        val __m = __v
        _output__.writeTag(2, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      typeSpec.varbit.foreach { __v =>
        val __m = __v
        _output__.writeTag(3, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      annotations.foreach { __v =>
        val __m = __v
        _output__.writeString(4, __m)
      };
      annotationLocations.foreach { __v =>
        val __m = __v
        _output__.writeTag(5, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      structuredAnnotations.foreach { __v =>
        val __m = __v
        _output__.writeTag(6, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      unknownFields.writeTo(_output__)
    }
    def getBit: p4.config.v1.p4types.P4BitTypeSpec = typeSpec.bit.getOrElse(p4.config.v1.p4types.P4BitTypeSpec.defaultInstance)
    def withBit(__v: p4.config.v1.p4types.P4BitTypeSpec): P4BitstringLikeTypeSpec = copy(typeSpec = p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Bit(__v))
    def getInt: p4.config.v1.p4types.P4IntTypeSpec = typeSpec.int.getOrElse(p4.config.v1.p4types.P4IntTypeSpec.defaultInstance)
    def withInt(__v: p4.config.v1.p4types.P4IntTypeSpec): P4BitstringLikeTypeSpec = copy(typeSpec = p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Int(__v))
    def getVarbit: p4.config.v1.p4types.P4VarbitTypeSpec = typeSpec.varbit.getOrElse(p4.config.v1.p4types.P4VarbitTypeSpec.defaultInstance)
    def withVarbit(__v: p4.config.v1.p4types.P4VarbitTypeSpec): P4BitstringLikeTypeSpec = copy(typeSpec = p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Varbit(__v))
    def clearAnnotations = copy(annotations = _root_.scala.Seq.empty)
    def addAnnotations(__vs: _root_.scala.Predef.String *): P4BitstringLikeTypeSpec = addAllAnnotations(__vs)
    def addAllAnnotations(__vs: Iterable[_root_.scala.Predef.String]): P4BitstringLikeTypeSpec = copy(annotations = annotations ++ __vs)
    def withAnnotations(__v: _root_.scala.Seq[_root_.scala.Predef.String]): P4BitstringLikeTypeSpec = copy(annotations = __v)
    def clearAnnotationLocations = copy(annotationLocations = _root_.scala.Seq.empty)
    def addAnnotationLocations(__vs: p4.config.v1.p4types.SourceLocation *): P4BitstringLikeTypeSpec = addAllAnnotationLocations(__vs)
    def addAllAnnotationLocations(__vs: Iterable[p4.config.v1.p4types.SourceLocation]): P4BitstringLikeTypeSpec = copy(annotationLocations = annotationLocations ++ __vs)
    def withAnnotationLocations(__v: _root_.scala.Seq[p4.config.v1.p4types.SourceLocation]): P4BitstringLikeTypeSpec = copy(annotationLocations = __v)
    def clearStructuredAnnotations = copy(structuredAnnotations = _root_.scala.Seq.empty)
    def addStructuredAnnotations(__vs: p4.config.v1.p4types.StructuredAnnotation *): P4BitstringLikeTypeSpec = addAllStructuredAnnotations(__vs)
    def addAllStructuredAnnotations(__vs: Iterable[p4.config.v1.p4types.StructuredAnnotation]): P4BitstringLikeTypeSpec = copy(structuredAnnotations = structuredAnnotations ++ __vs)
    def withStructuredAnnotations(__v: _root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation]): P4BitstringLikeTypeSpec = copy(structuredAnnotations = __v)
    def clearTypeSpec: P4BitstringLikeTypeSpec = copy(typeSpec = p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Empty)
    def withTypeSpec(__v: p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec): P4BitstringLikeTypeSpec = copy(typeSpec = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => typeSpec.bit.orNull
        case 2 => typeSpec.int.orNull
        case 3 => typeSpec.varbit.orNull
        case 4 => annotations
        case 5 => annotationLocations
        case 6 => structuredAnnotations
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => typeSpec.bit.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 2 => typeSpec.int.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 3 => typeSpec.varbit.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 4 => _root_.scalapb.descriptors.PRepeated(annotations.iterator.map(_root_.scalapb.descriptors.PString(_)).toVector)
        case 5 => _root_.scalapb.descriptors.PRepeated(annotationLocations.iterator.map(_.toPMessage).toVector)
        case 6 => _root_.scalapb.descriptors.PRepeated(structuredAnnotations.iterator.map(_.toPMessage).toVector)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: p4.config.v1.p4types.P4BitstringLikeTypeSpec.type = p4.config.v1.p4types.P4BitstringLikeTypeSpec
    // @@protoc_insertion_point(GeneratedMessage[p4.config.v1.P4BitstringLikeTypeSpec])
}

object P4BitstringLikeTypeSpec extends scalapb.GeneratedMessageCompanion[p4.config.v1.p4types.P4BitstringLikeTypeSpec] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[p4.config.v1.p4types.P4BitstringLikeTypeSpec] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): p4.config.v1.p4types.P4BitstringLikeTypeSpec = {
    val __annotations: _root_.scala.collection.immutable.VectorBuilder[_root_.scala.Predef.String] = new _root_.scala.collection.immutable.VectorBuilder[_root_.scala.Predef.String]
    val __annotationLocations: _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.SourceLocation] = new _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.SourceLocation]
    val __structuredAnnotations: _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.StructuredAnnotation] = new _root_.scala.collection.immutable.VectorBuilder[p4.config.v1.p4types.StructuredAnnotation]
    var __typeSpec: p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec = p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Empty
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __typeSpec = p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Bit(__typeSpec.bit.fold(_root_.scalapb.LiteParser.readMessage[p4.config.v1.p4types.P4BitTypeSpec](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 18 =>
          __typeSpec = p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Int(__typeSpec.int.fold(_root_.scalapb.LiteParser.readMessage[p4.config.v1.p4types.P4IntTypeSpec](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 26 =>
          __typeSpec = p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Varbit(__typeSpec.varbit.fold(_root_.scalapb.LiteParser.readMessage[p4.config.v1.p4types.P4VarbitTypeSpec](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 34 =>
          __annotations += _input__.readStringRequireUtf8()
        case 42 =>
          __annotationLocations += _root_.scalapb.LiteParser.readMessage[p4.config.v1.p4types.SourceLocation](_input__)
        case 50 =>
          __structuredAnnotations += _root_.scalapb.LiteParser.readMessage[p4.config.v1.p4types.StructuredAnnotation](_input__)
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    p4.config.v1.p4types.P4BitstringLikeTypeSpec(
        annotations = __annotations.result(),
        annotationLocations = __annotationLocations.result(),
        structuredAnnotations = __structuredAnnotations.result(),
        typeSpec = __typeSpec,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[p4.config.v1.p4types.P4BitstringLikeTypeSpec] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      p4.config.v1.p4types.P4BitstringLikeTypeSpec(
        annotations = __fieldsMap.get(scalaDescriptor.findFieldByNumber(4).get).map(_.as[_root_.scala.Seq[_root_.scala.Predef.String]]).getOrElse(_root_.scala.Seq.empty),
        annotationLocations = __fieldsMap.get(scalaDescriptor.findFieldByNumber(5).get).map(_.as[_root_.scala.Seq[p4.config.v1.p4types.SourceLocation]]).getOrElse(_root_.scala.Seq.empty),
        structuredAnnotations = __fieldsMap.get(scalaDescriptor.findFieldByNumber(6).get).map(_.as[_root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation]]).getOrElse(_root_.scala.Seq.empty),
        typeSpec = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).flatMap(_.as[_root_.scala.Option[p4.config.v1.p4types.P4BitTypeSpec]]).map(p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Bit(_))
            .orElse[p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec](__fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).flatMap(_.as[_root_.scala.Option[p4.config.v1.p4types.P4IntTypeSpec]]).map(p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Int(_)))
            .orElse[p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec](__fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).flatMap(_.as[_root_.scala.Option[p4.config.v1.p4types.P4VarbitTypeSpec]]).map(p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Varbit(_)))
            .getOrElse(p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Empty)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = P4TypesProto.javaDescriptor.getMessageTypes().get(5)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = P4TypesProto.scalaDescriptor.messages(5)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = p4.config.v1.p4types.P4BitTypeSpec
      case 2 => __out = p4.config.v1.p4types.P4IntTypeSpec
      case 3 => __out = p4.config.v1.p4types.P4VarbitTypeSpec
      case 5 => __out = p4.config.v1.p4types.SourceLocation
      case 6 => __out = p4.config.v1.p4types.StructuredAnnotation
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = p4.config.v1.p4types.P4BitstringLikeTypeSpec(
    annotations = _root_.scala.Seq.empty,
    annotationLocations = _root_.scala.Seq.empty,
    structuredAnnotations = _root_.scala.Seq.empty,
    typeSpec = p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Empty
  )
  sealed trait TypeSpec extends _root_.scalapb.GeneratedOneof {
    def isEmpty: _root_.scala.Boolean = false
    def isDefined: _root_.scala.Boolean = true
    def isBit: _root_.scala.Boolean = false
    def isInt: _root_.scala.Boolean = false
    def isVarbit: _root_.scala.Boolean = false
    def bit: _root_.scala.Option[p4.config.v1.p4types.P4BitTypeSpec] = _root_.scala.None
    def int: _root_.scala.Option[p4.config.v1.p4types.P4IntTypeSpec] = _root_.scala.None
    def varbit: _root_.scala.Option[p4.config.v1.p4types.P4VarbitTypeSpec] = _root_.scala.None
  }
  object TypeSpec {
    @SerialVersionUID(0L)
    case object Empty extends p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec {
      type ValueType = _root_.scala.Nothing
      override def isEmpty: _root_.scala.Boolean = true
      override def isDefined: _root_.scala.Boolean = false
      override def number: _root_.scala.Int = 0
      override def value: _root_.scala.Nothing = throw new java.util.NoSuchElementException("Empty.value")
    }
  
    @SerialVersionUID(0L)
    final case class Bit(value: p4.config.v1.p4types.P4BitTypeSpec) extends p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec {
      type ValueType = p4.config.v1.p4types.P4BitTypeSpec
      override def isBit: _root_.scala.Boolean = true
      override def bit: _root_.scala.Option[p4.config.v1.p4types.P4BitTypeSpec] = Some(value)
      override def number: _root_.scala.Int = 1
    }
    @SerialVersionUID(0L)
    final case class Int(value: p4.config.v1.p4types.P4IntTypeSpec) extends p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec {
      type ValueType = p4.config.v1.p4types.P4IntTypeSpec
      override def isInt: _root_.scala.Boolean = true
      override def int: _root_.scala.Option[p4.config.v1.p4types.P4IntTypeSpec] = Some(value)
      override def number: _root_.scala.Int = 2
    }
    @SerialVersionUID(0L)
    final case class Varbit(value: p4.config.v1.p4types.P4VarbitTypeSpec) extends p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec {
      type ValueType = p4.config.v1.p4types.P4VarbitTypeSpec
      override def isVarbit: _root_.scala.Boolean = true
      override def varbit: _root_.scala.Option[p4.config.v1.p4types.P4VarbitTypeSpec] = Some(value)
      override def number: _root_.scala.Int = 3
    }
  }
  implicit class P4BitstringLikeTypeSpecLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4types.P4BitstringLikeTypeSpec]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, p4.config.v1.p4types.P4BitstringLikeTypeSpec](_l) {
    def bit: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4types.P4BitTypeSpec] = field(_.getBit)((c_, f_) => c_.copy(typeSpec = p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Bit(f_)))
    def int: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4types.P4IntTypeSpec] = field(_.getInt)((c_, f_) => c_.copy(typeSpec = p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Int(f_)))
    def varbit: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4types.P4VarbitTypeSpec] = field(_.getVarbit)((c_, f_) => c_.copy(typeSpec = p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec.Varbit(f_)))
    def annotations: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[_root_.scala.Predef.String]] = field(_.annotations)((c_, f_) => c_.copy(annotations = f_))
    def annotationLocations: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[p4.config.v1.p4types.SourceLocation]] = field(_.annotationLocations)((c_, f_) => c_.copy(annotationLocations = f_))
    def structuredAnnotations: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation]] = field(_.structuredAnnotations)((c_, f_) => c_.copy(structuredAnnotations = f_))
    def typeSpec: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec] = field(_.typeSpec)((c_, f_) => c_.copy(typeSpec = f_))
  }
  final val BIT_FIELD_NUMBER = 1
  final val INT_FIELD_NUMBER = 2
  final val VARBIT_FIELD_NUMBER = 3
  final val ANNOTATIONS_FIELD_NUMBER = 4
  final val ANNOTATION_LOCATIONS_FIELD_NUMBER = 5
  final val STRUCTURED_ANNOTATIONS_FIELD_NUMBER = 6
  def of(
    typeSpec: p4.config.v1.p4types.P4BitstringLikeTypeSpec.TypeSpec,
    annotations: _root_.scala.Seq[_root_.scala.Predef.String],
    annotationLocations: _root_.scala.Seq[p4.config.v1.p4types.SourceLocation],
    structuredAnnotations: _root_.scala.Seq[p4.config.v1.p4types.StructuredAnnotation]
  ): _root_.p4.config.v1.p4types.P4BitstringLikeTypeSpec = _root_.p4.config.v1.p4types.P4BitstringLikeTypeSpec(
    typeSpec,
    annotations,
    annotationLocations,
    structuredAnnotations
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[p4.config.v1.P4BitstringLikeTypeSpec])
}
