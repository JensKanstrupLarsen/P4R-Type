// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package p4.v1.p4runtime

@SerialVersionUID(0L)
final case class GetForwardingPipelineConfigResponse(
    config: _root_.scala.Option[p4.v1.p4runtime.ForwardingPipelineConfig] = _root_.scala.None,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[GetForwardingPipelineConfigResponse] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      if (config.isDefined) {
        val __value = config.get
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
      config.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      unknownFields.writeTo(_output__)
    }
    def getConfig: p4.v1.p4runtime.ForwardingPipelineConfig = config.getOrElse(p4.v1.p4runtime.ForwardingPipelineConfig.defaultInstance)
    def clearConfig: GetForwardingPipelineConfigResponse = copy(config = _root_.scala.None)
    def withConfig(__v: p4.v1.p4runtime.ForwardingPipelineConfig): GetForwardingPipelineConfigResponse = copy(config = Option(__v))
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => config.orNull
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => config.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: p4.v1.p4runtime.GetForwardingPipelineConfigResponse.type = p4.v1.p4runtime.GetForwardingPipelineConfigResponse
    // @@protoc_insertion_point(GeneratedMessage[p4.v1.GetForwardingPipelineConfigResponse])
}

object GetForwardingPipelineConfigResponse extends scalapb.GeneratedMessageCompanion[p4.v1.p4runtime.GetForwardingPipelineConfigResponse] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[p4.v1.p4runtime.GetForwardingPipelineConfigResponse] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): p4.v1.p4runtime.GetForwardingPipelineConfigResponse = {
    var __config: _root_.scala.Option[p4.v1.p4runtime.ForwardingPipelineConfig] = _root_.scala.None
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __config = Option(__config.fold(_root_.scalapb.LiteParser.readMessage[p4.v1.p4runtime.ForwardingPipelineConfig](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    p4.v1.p4runtime.GetForwardingPipelineConfigResponse(
        config = __config,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[p4.v1.p4runtime.GetForwardingPipelineConfigResponse] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      p4.v1.p4runtime.GetForwardingPipelineConfigResponse(
        config = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).flatMap(_.as[_root_.scala.Option[p4.v1.p4runtime.ForwardingPipelineConfig]])
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = P4RuntimeProto.javaDescriptor.getMessageTypes().get(50)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = P4RuntimeProto.scalaDescriptor.messages(50)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = p4.v1.p4runtime.ForwardingPipelineConfig
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = p4.v1.p4runtime.GetForwardingPipelineConfigResponse(
    config = _root_.scala.None
  )
  implicit class GetForwardingPipelineConfigResponseLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, p4.v1.p4runtime.GetForwardingPipelineConfigResponse]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, p4.v1.p4runtime.GetForwardingPipelineConfigResponse](_l) {
    def config: _root_.scalapb.lenses.Lens[UpperPB, p4.v1.p4runtime.ForwardingPipelineConfig] = field(_.getConfig)((c_, f_) => c_.copy(config = Option(f_)))
    def optionalConfig: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[p4.v1.p4runtime.ForwardingPipelineConfig]] = field(_.config)((c_, f_) => c_.copy(config = f_))
  }
  final val CONFIG_FIELD_NUMBER = 1
  def of(
    config: _root_.scala.Option[p4.v1.p4runtime.ForwardingPipelineConfig]
  ): _root_.p4.v1.p4runtime.GetForwardingPipelineConfigResponse = _root_.p4.v1.p4runtime.GetForwardingPipelineConfigResponse(
    config
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[p4.v1.GetForwardingPipelineConfigResponse])
}
