// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package p4.v1.p4data

/** @param bitwidth
  *   dynamic bitwidth of the field
  */
@SerialVersionUID(0L)
final case class P4Varbit(
    bitstring: _root_.com.google.protobuf.ByteString = _root_.com.google.protobuf.ByteString.EMPTY,
    bitwidth: _root_.scala.Int = 0,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[P4Varbit] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = bitstring
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeBytesSize(1, __value)
        }
      };
      
      {
        val __value = bitwidth
        if (__value != 0) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeInt32Size(2, __value)
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
      {
        val __v = bitstring
        if (!__v.isEmpty) {
          _output__.writeBytes(1, __v)
        }
      };
      {
        val __v = bitwidth
        if (__v != 0) {
          _output__.writeInt32(2, __v)
        }
      };
      unknownFields.writeTo(_output__)
    }
    def withBitstring(__v: _root_.com.google.protobuf.ByteString): P4Varbit = copy(bitstring = __v)
    def withBitwidth(__v: _root_.scala.Int): P4Varbit = copy(bitwidth = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = bitstring
          if (__t != _root_.com.google.protobuf.ByteString.EMPTY) __t else null
        }
        case 2 => {
          val __t = bitwidth
          if (__t != 0) __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PByteString(bitstring)
        case 2 => _root_.scalapb.descriptors.PInt(bitwidth)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: p4.v1.p4data.P4Varbit.type = p4.v1.p4data.P4Varbit
    // @@protoc_insertion_point(GeneratedMessage[p4.v1.P4Varbit])
}

object P4Varbit extends scalapb.GeneratedMessageCompanion[p4.v1.p4data.P4Varbit] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[p4.v1.p4data.P4Varbit] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): p4.v1.p4data.P4Varbit = {
    var __bitstring: _root_.com.google.protobuf.ByteString = _root_.com.google.protobuf.ByteString.EMPTY
    var __bitwidth: _root_.scala.Int = 0
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __bitstring = _input__.readBytes()
        case 16 =>
          __bitwidth = _input__.readInt32()
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    p4.v1.p4data.P4Varbit(
        bitstring = __bitstring,
        bitwidth = __bitwidth,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[p4.v1.p4data.P4Varbit] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      p4.v1.p4data.P4Varbit(
        bitstring = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.com.google.protobuf.ByteString]).getOrElse(_root_.com.google.protobuf.ByteString.EMPTY),
        bitwidth = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Int]).getOrElse(0)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = P4DataProto.javaDescriptor.getMessageTypes().get(1)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = P4DataProto.scalaDescriptor.messages(1)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = p4.v1.p4data.P4Varbit(
    bitstring = _root_.com.google.protobuf.ByteString.EMPTY,
    bitwidth = 0
  )
  implicit class P4VarbitLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, p4.v1.p4data.P4Varbit]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, p4.v1.p4data.P4Varbit](_l) {
    def bitstring: _root_.scalapb.lenses.Lens[UpperPB, _root_.com.google.protobuf.ByteString] = field(_.bitstring)((c_, f_) => c_.copy(bitstring = f_))
    def bitwidth: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Int] = field(_.bitwidth)((c_, f_) => c_.copy(bitwidth = f_))
  }
  final val BITSTRING_FIELD_NUMBER = 1
  final val BITWIDTH_FIELD_NUMBER = 2
  def of(
    bitstring: _root_.com.google.protobuf.ByteString,
    bitwidth: _root_.scala.Int
  ): _root_.p4.v1.p4data.P4Varbit = _root_.p4.v1.p4data.P4Varbit(
    bitstring,
    bitwidth
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[p4.v1.P4Varbit])
}