// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package p4.config.v1.p4info

/** @param directTableId
  *   the id of the table to which the counter is attached
  */
@SerialVersionUID(0L)
final case class DirectCounter(
    preamble: _root_.scala.Option[p4.config.v1.p4info.Preamble] = _root_.scala.None,
    spec: _root_.scala.Option[p4.config.v1.p4info.CounterSpec] = _root_.scala.None,
    directTableId: _root_.scala.Int = 0,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[DirectCounter] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      if (preamble.isDefined) {
        val __value = preamble.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (spec.isDefined) {
        val __value = spec.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      
      {
        val __value = directTableId
        if (__value != 0) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeUInt32Size(3, __value)
        }
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
      preamble.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      spec.foreach { __v =>
        val __m = __v
        _output__.writeTag(2, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      {
        val __v = directTableId
        if (__v != 0) {
          _output__.writeUInt32(3, __v)
        }
      };
      unknownFields.writeTo(_output__)
    }
    def getPreamble: p4.config.v1.p4info.Preamble = preamble.getOrElse(p4.config.v1.p4info.Preamble.defaultInstance)
    def clearPreamble: DirectCounter = copy(preamble = _root_.scala.None)
    def withPreamble(__v: p4.config.v1.p4info.Preamble): DirectCounter = copy(preamble = Option(__v))
    def getSpec: p4.config.v1.p4info.CounterSpec = spec.getOrElse(p4.config.v1.p4info.CounterSpec.defaultInstance)
    def clearSpec: DirectCounter = copy(spec = _root_.scala.None)
    def withSpec(__v: p4.config.v1.p4info.CounterSpec): DirectCounter = copy(spec = Option(__v))
    def withDirectTableId(__v: _root_.scala.Int): DirectCounter = copy(directTableId = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => preamble.orNull
        case 2 => spec.orNull
        case 3 => {
          val __t = directTableId
          if (__t != 0) __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => preamble.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 2 => spec.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 3 => _root_.scalapb.descriptors.PInt(directTableId)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: p4.config.v1.p4info.DirectCounter.type = p4.config.v1.p4info.DirectCounter
    // @@protoc_insertion_point(GeneratedMessage[p4.config.v1.DirectCounter])
}

object DirectCounter extends scalapb.GeneratedMessageCompanion[p4.config.v1.p4info.DirectCounter] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[p4.config.v1.p4info.DirectCounter] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): p4.config.v1.p4info.DirectCounter = {
    var __preamble: _root_.scala.Option[p4.config.v1.p4info.Preamble] = _root_.scala.None
    var __spec: _root_.scala.Option[p4.config.v1.p4info.CounterSpec] = _root_.scala.None
    var __directTableId: _root_.scala.Int = 0
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __preamble = Option(__preamble.fold(_root_.scalapb.LiteParser.readMessage[p4.config.v1.p4info.Preamble](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 18 =>
          __spec = Option(__spec.fold(_root_.scalapb.LiteParser.readMessage[p4.config.v1.p4info.CounterSpec](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 24 =>
          __directTableId = _input__.readUInt32()
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    p4.config.v1.p4info.DirectCounter(
        preamble = __preamble,
        spec = __spec,
        directTableId = __directTableId,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[p4.config.v1.p4info.DirectCounter] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      p4.config.v1.p4info.DirectCounter(
        preamble = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).flatMap(_.as[_root_.scala.Option[p4.config.v1.p4info.Preamble]]),
        spec = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).flatMap(_.as[_root_.scala.Option[p4.config.v1.p4info.CounterSpec]]),
        directTableId = __fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).map(_.as[_root_.scala.Int]).getOrElse(0)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = P4InfoProto.javaDescriptor.getMessageTypes().get(14)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = P4InfoProto.scalaDescriptor.messages(14)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = p4.config.v1.p4info.Preamble
      case 2 => __out = p4.config.v1.p4info.CounterSpec
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = p4.config.v1.p4info.DirectCounter(
    preamble = _root_.scala.None,
    spec = _root_.scala.None,
    directTableId = 0
  )
  implicit class DirectCounterLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4info.DirectCounter]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, p4.config.v1.p4info.DirectCounter](_l) {
    def preamble: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4info.Preamble] = field(_.getPreamble)((c_, f_) => c_.copy(preamble = Option(f_)))
    def optionalPreamble: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[p4.config.v1.p4info.Preamble]] = field(_.preamble)((c_, f_) => c_.copy(preamble = f_))
    def spec: _root_.scalapb.lenses.Lens[UpperPB, p4.config.v1.p4info.CounterSpec] = field(_.getSpec)((c_, f_) => c_.copy(spec = Option(f_)))
    def optionalSpec: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[p4.config.v1.p4info.CounterSpec]] = field(_.spec)((c_, f_) => c_.copy(spec = f_))
    def directTableId: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Int] = field(_.directTableId)((c_, f_) => c_.copy(directTableId = f_))
  }
  final val PREAMBLE_FIELD_NUMBER = 1
  final val SPEC_FIELD_NUMBER = 2
  final val DIRECT_TABLE_ID_FIELD_NUMBER = 3
  def of(
    preamble: _root_.scala.Option[p4.config.v1.p4info.Preamble],
    spec: _root_.scala.Option[p4.config.v1.p4info.CounterSpec],
    directTableId: _root_.scala.Int
  ): _root_.p4.config.v1.p4info.DirectCounter = _root_.p4.config.v1.p4info.DirectCounter(
    preamble,
    spec,
    directTableId
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[p4.config.v1.DirectCounter])
}
