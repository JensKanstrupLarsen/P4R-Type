// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package p4.v1.p4data

/** @param isValid
  *   If the header is invalid (is_valid is "false"), then the bitstrings
  *   repeated field must be empty.
  */
@SerialVersionUID(0L)
final case class P4Header(
    isValid: _root_.scala.Boolean = false,
    bitstrings: _root_.scala.Seq[_root_.com.google.protobuf.ByteString] = _root_.scala.Seq.empty,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[P4Header] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = isValid
        if (__value != false) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeBoolSize(1, __value)
        }
      };
      bitstrings.foreach { __item =>
        val __value = __item
        __size += _root_.com.google.protobuf.CodedOutputStream.computeBytesSize(2, __value)
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
        val __v = isValid
        if (__v != false) {
          _output__.writeBool(1, __v)
        }
      };
      bitstrings.foreach { __v =>
        val __m = __v
        _output__.writeBytes(2, __m)
      };
      unknownFields.writeTo(_output__)
    }
    def withIsValid(__v: _root_.scala.Boolean): P4Header = copy(isValid = __v)
    def clearBitstrings = copy(bitstrings = _root_.scala.Seq.empty)
    def addBitstrings(__vs: _root_.com.google.protobuf.ByteString *): P4Header = addAllBitstrings(__vs)
    def addAllBitstrings(__vs: Iterable[_root_.com.google.protobuf.ByteString]): P4Header = copy(bitstrings = bitstrings ++ __vs)
    def withBitstrings(__v: _root_.scala.Seq[_root_.com.google.protobuf.ByteString]): P4Header = copy(bitstrings = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = isValid
          if (__t != false) __t else null
        }
        case 2 => bitstrings
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PBoolean(isValid)
        case 2 => _root_.scalapb.descriptors.PRepeated(bitstrings.iterator.map(_root_.scalapb.descriptors.PByteString(_)).toVector)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: p4.v1.p4data.P4Header.type = p4.v1.p4data.P4Header
    // @@protoc_insertion_point(GeneratedMessage[p4.v1.P4Header])
}

object P4Header extends scalapb.GeneratedMessageCompanion[p4.v1.p4data.P4Header] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[p4.v1.p4data.P4Header] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): p4.v1.p4data.P4Header = {
    var __isValid: _root_.scala.Boolean = false
    val __bitstrings: _root_.scala.collection.immutable.VectorBuilder[_root_.com.google.protobuf.ByteString] = new _root_.scala.collection.immutable.VectorBuilder[_root_.com.google.protobuf.ByteString]
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 8 =>
          __isValid = _input__.readBool()
        case 18 =>
          __bitstrings += _input__.readBytes()
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    p4.v1.p4data.P4Header(
        isValid = __isValid,
        bitstrings = __bitstrings.result(),
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[p4.v1.p4data.P4Header] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      p4.v1.p4data.P4Header(
        isValid = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Boolean]).getOrElse(false),
        bitstrings = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Seq[_root_.com.google.protobuf.ByteString]]).getOrElse(_root_.scala.Seq.empty)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = P4DataProto.javaDescriptor.getMessageTypes().get(3)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = P4DataProto.scalaDescriptor.messages(3)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = p4.v1.p4data.P4Header(
    isValid = false,
    bitstrings = _root_.scala.Seq.empty
  )
  implicit class P4HeaderLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, p4.v1.p4data.P4Header]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, p4.v1.p4data.P4Header](_l) {
    def isValid: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Boolean] = field(_.isValid)((c_, f_) => c_.copy(isValid = f_))
    def bitstrings: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[_root_.com.google.protobuf.ByteString]] = field(_.bitstrings)((c_, f_) => c_.copy(bitstrings = f_))
  }
  final val IS_VALID_FIELD_NUMBER = 1
  final val BITSTRINGS_FIELD_NUMBER = 2
  def of(
    isValid: _root_.scala.Boolean,
    bitstrings: _root_.scala.Seq[_root_.com.google.protobuf.ByteString]
  ): _root_.p4.v1.p4data.P4Header = _root_.p4.v1.p4data.P4Header(
    isValid,
    bitstrings
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[p4.v1.P4Header])
}
