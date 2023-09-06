// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package p4.v1.p4runtime

/** table_actions ::= action_specification | action_profile_specification
  */
@SerialVersionUID(0L)
final case class TableAction(
    `type`: p4.v1.p4runtime.TableAction.Type = p4.v1.p4runtime.TableAction.Type.Empty,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[TableAction] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      if (`type`.action.isDefined) {
        val __value = `type`.action.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (`type`.actionProfileMemberId.isDefined) {
        val __value = `type`.actionProfileMemberId.get
        __size += _root_.com.google.protobuf.CodedOutputStream.computeUInt32Size(2, __value)
      };
      if (`type`.actionProfileGroupId.isDefined) {
        val __value = `type`.actionProfileGroupId.get
        __size += _root_.com.google.protobuf.CodedOutputStream.computeUInt32Size(3, __value)
      };
      if (`type`.actionProfileActionSet.isDefined) {
        val __value = `type`.actionProfileActionSet.get
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
      `type`.action.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      `type`.actionProfileMemberId.foreach { __v =>
        val __m = __v
        _output__.writeUInt32(2, __m)
      };
      `type`.actionProfileGroupId.foreach { __v =>
        val __m = __v
        _output__.writeUInt32(3, __m)
      };
      `type`.actionProfileActionSet.foreach { __v =>
        val __m = __v
        _output__.writeTag(4, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      unknownFields.writeTo(_output__)
    }
    def getAction: p4.v1.p4runtime.Action = `type`.action.getOrElse(p4.v1.p4runtime.Action.defaultInstance)
    def withAction(__v: p4.v1.p4runtime.Action): TableAction = copy(`type` = p4.v1.p4runtime.TableAction.Type.Action(__v))
    def getActionProfileMemberId: _root_.scala.Int = `type`.actionProfileMemberId.getOrElse(0)
    def withActionProfileMemberId(__v: _root_.scala.Int): TableAction = copy(`type` = p4.v1.p4runtime.TableAction.Type.ActionProfileMemberId(__v))
    def getActionProfileGroupId: _root_.scala.Int = `type`.actionProfileGroupId.getOrElse(0)
    def withActionProfileGroupId(__v: _root_.scala.Int): TableAction = copy(`type` = p4.v1.p4runtime.TableAction.Type.ActionProfileGroupId(__v))
    def getActionProfileActionSet: p4.v1.p4runtime.ActionProfileActionSet = `type`.actionProfileActionSet.getOrElse(p4.v1.p4runtime.ActionProfileActionSet.defaultInstance)
    def withActionProfileActionSet(__v: p4.v1.p4runtime.ActionProfileActionSet): TableAction = copy(`type` = p4.v1.p4runtime.TableAction.Type.ActionProfileActionSet(__v))
    def clearType: TableAction = copy(`type` = p4.v1.p4runtime.TableAction.Type.Empty)
    def withType(__v: p4.v1.p4runtime.TableAction.Type): TableAction = copy(`type` = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => `type`.action.orNull
        case 2 => `type`.actionProfileMemberId.orNull
        case 3 => `type`.actionProfileGroupId.orNull
        case 4 => `type`.actionProfileActionSet.orNull
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => `type`.action.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 2 => `type`.actionProfileMemberId.map(_root_.scalapb.descriptors.PInt(_)).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 3 => `type`.actionProfileGroupId.map(_root_.scalapb.descriptors.PInt(_)).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 4 => `type`.actionProfileActionSet.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: p4.v1.p4runtime.TableAction.type = p4.v1.p4runtime.TableAction
    // @@protoc_insertion_point(GeneratedMessage[p4.v1.TableAction])
}

object TableAction extends scalapb.GeneratedMessageCompanion[p4.v1.p4runtime.TableAction] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[p4.v1.p4runtime.TableAction] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): p4.v1.p4runtime.TableAction = {
    var __type: p4.v1.p4runtime.TableAction.Type = p4.v1.p4runtime.TableAction.Type.Empty
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __type = p4.v1.p4runtime.TableAction.Type.Action(__type.action.fold(_root_.scalapb.LiteParser.readMessage[p4.v1.p4runtime.Action](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 16 =>
          __type = p4.v1.p4runtime.TableAction.Type.ActionProfileMemberId(_input__.readUInt32())
        case 24 =>
          __type = p4.v1.p4runtime.TableAction.Type.ActionProfileGroupId(_input__.readUInt32())
        case 34 =>
          __type = p4.v1.p4runtime.TableAction.Type.ActionProfileActionSet(__type.actionProfileActionSet.fold(_root_.scalapb.LiteParser.readMessage[p4.v1.p4runtime.ActionProfileActionSet](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    p4.v1.p4runtime.TableAction(
        `type` = __type,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[p4.v1.p4runtime.TableAction] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      p4.v1.p4runtime.TableAction(
        `type` = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).flatMap(_.as[_root_.scala.Option[p4.v1.p4runtime.Action]]).map(p4.v1.p4runtime.TableAction.Type.Action(_))
            .orElse[p4.v1.p4runtime.TableAction.Type](__fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).flatMap(_.as[_root_.scala.Option[_root_.scala.Int]]).map(p4.v1.p4runtime.TableAction.Type.ActionProfileMemberId(_)))
            .orElse[p4.v1.p4runtime.TableAction.Type](__fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).flatMap(_.as[_root_.scala.Option[_root_.scala.Int]]).map(p4.v1.p4runtime.TableAction.Type.ActionProfileGroupId(_)))
            .orElse[p4.v1.p4runtime.TableAction.Type](__fieldsMap.get(scalaDescriptor.findFieldByNumber(4).get).flatMap(_.as[_root_.scala.Option[p4.v1.p4runtime.ActionProfileActionSet]]).map(p4.v1.p4runtime.TableAction.Type.ActionProfileActionSet(_)))
            .getOrElse(p4.v1.p4runtime.TableAction.Type.Empty)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = P4RuntimeProto.javaDescriptor.getMessageTypes().get(9)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = P4RuntimeProto.scalaDescriptor.messages(9)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = p4.v1.p4runtime.Action
      case 4 => __out = p4.v1.p4runtime.ActionProfileActionSet
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = p4.v1.p4runtime.TableAction(
    `type` = p4.v1.p4runtime.TableAction.Type.Empty
  )
  sealed trait Type extends _root_.scalapb.GeneratedOneof {
    def isEmpty: _root_.scala.Boolean = false
    def isDefined: _root_.scala.Boolean = true
    def isAction: _root_.scala.Boolean = false
    def isActionProfileMemberId: _root_.scala.Boolean = false
    def isActionProfileGroupId: _root_.scala.Boolean = false
    def isActionProfileActionSet: _root_.scala.Boolean = false
    def action: _root_.scala.Option[p4.v1.p4runtime.Action] = _root_.scala.None
    def actionProfileMemberId: _root_.scala.Option[_root_.scala.Int] = _root_.scala.None
    def actionProfileGroupId: _root_.scala.Option[_root_.scala.Int] = _root_.scala.None
    def actionProfileActionSet: _root_.scala.Option[p4.v1.p4runtime.ActionProfileActionSet] = _root_.scala.None
  }
  object Type {
    @SerialVersionUID(0L)
    case object Empty extends p4.v1.p4runtime.TableAction.Type {
      type ValueType = _root_.scala.Nothing
      override def isEmpty: _root_.scala.Boolean = true
      override def isDefined: _root_.scala.Boolean = false
      override def number: _root_.scala.Int = 0
      override def value: _root_.scala.Nothing = throw new java.util.NoSuchElementException("Empty.value")
    }
  
    @SerialVersionUID(0L)
    final case class Action(value: p4.v1.p4runtime.Action) extends p4.v1.p4runtime.TableAction.Type {
      type ValueType = p4.v1.p4runtime.Action
      override def isAction: _root_.scala.Boolean = true
      override def action: _root_.scala.Option[p4.v1.p4runtime.Action] = Some(value)
      override def number: _root_.scala.Int = 1
    }
    @SerialVersionUID(0L)
    final case class ActionProfileMemberId(value: _root_.scala.Int) extends p4.v1.p4runtime.TableAction.Type {
      type ValueType = _root_.scala.Int
      override def isActionProfileMemberId: _root_.scala.Boolean = true
      override def actionProfileMemberId: _root_.scala.Option[_root_.scala.Int] = Some(value)
      override def number: _root_.scala.Int = 2
    }
    @SerialVersionUID(0L)
    final case class ActionProfileGroupId(value: _root_.scala.Int) extends p4.v1.p4runtime.TableAction.Type {
      type ValueType = _root_.scala.Int
      override def isActionProfileGroupId: _root_.scala.Boolean = true
      override def actionProfileGroupId: _root_.scala.Option[_root_.scala.Int] = Some(value)
      override def number: _root_.scala.Int = 3
    }
    @SerialVersionUID(0L)
    final case class ActionProfileActionSet(value: p4.v1.p4runtime.ActionProfileActionSet) extends p4.v1.p4runtime.TableAction.Type {
      type ValueType = p4.v1.p4runtime.ActionProfileActionSet
      override def isActionProfileActionSet: _root_.scala.Boolean = true
      override def actionProfileActionSet: _root_.scala.Option[p4.v1.p4runtime.ActionProfileActionSet] = Some(value)
      override def number: _root_.scala.Int = 4
    }
  }
  implicit class TableActionLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, p4.v1.p4runtime.TableAction]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, p4.v1.p4runtime.TableAction](_l) {
    def action: _root_.scalapb.lenses.Lens[UpperPB, p4.v1.p4runtime.Action] = field(_.getAction)((c_, f_) => c_.copy(`type` = p4.v1.p4runtime.TableAction.Type.Action(f_)))
    def actionProfileMemberId: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Int] = field(_.getActionProfileMemberId)((c_, f_) => c_.copy(`type` = p4.v1.p4runtime.TableAction.Type.ActionProfileMemberId(f_)))
    def actionProfileGroupId: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Int] = field(_.getActionProfileGroupId)((c_, f_) => c_.copy(`type` = p4.v1.p4runtime.TableAction.Type.ActionProfileGroupId(f_)))
    def actionProfileActionSet: _root_.scalapb.lenses.Lens[UpperPB, p4.v1.p4runtime.ActionProfileActionSet] = field(_.getActionProfileActionSet)((c_, f_) => c_.copy(`type` = p4.v1.p4runtime.TableAction.Type.ActionProfileActionSet(f_)))
    def `type`: _root_.scalapb.lenses.Lens[UpperPB, p4.v1.p4runtime.TableAction.Type] = field(_.`type`)((c_, f_) => c_.copy(`type` = f_))
  }
  final val ACTION_FIELD_NUMBER = 1
  final val ACTION_PROFILE_MEMBER_ID_FIELD_NUMBER = 2
  final val ACTION_PROFILE_GROUP_ID_FIELD_NUMBER = 3
  final val ACTION_PROFILE_ACTION_SET_FIELD_NUMBER = 4
  def of(
    `type`: p4.v1.p4runtime.TableAction.Type
  ): _root_.p4.v1.p4runtime.TableAction = _root_.p4.v1.p4runtime.TableAction(
    `type`
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[p4.v1.TableAction])
}