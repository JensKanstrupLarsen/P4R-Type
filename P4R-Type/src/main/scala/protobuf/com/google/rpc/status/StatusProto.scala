// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.google.rpc.status

object StatusProto extends _root_.scalapb.GeneratedFileObject {
  lazy val dependencies: Seq[_root_.scalapb.GeneratedFileObject] = Seq(
    com.google.protobuf.any.AnyProto
  )
  lazy val messagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] =
    Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]](
      com.google.rpc.status.Status
    )
  private lazy val ProtoBytes: _root_.scala.Array[Byte] =
      scalapb.Encoding.fromBase64(scala.collection.immutable.Seq(
  """Chdnb29nbGUvcnBjL3N0YXR1cy5wcm90bxIKZ29vZ2xlLnJwYxoZZ29vZ2xlL3Byb3RvYnVmL2FueS5wcm90byKNAQoGU3Rhd
  HVzEh0KBGNvZGUYASABKAVCCeI/BhIEY29kZVIEY29kZRImCgdtZXNzYWdlGAIgASgJQgziPwkSB21lc3NhZ2VSB21lc3NhZ2USP
  AoHZGV0YWlscxgDIAMoCzIULmdvb2dsZS5wcm90b2J1Zi5BbnlCDOI/CRIHZGV0YWlsc1IHZGV0YWlsc0IfCg5jb20uZ29vZ2xlL
  nJwY0ILU3RhdHVzUHJvdG9QAWIGcHJvdG8z"""
      ).mkString)
  lazy val scalaDescriptor: _root_.scalapb.descriptors.FileDescriptor = {
    val scalaProto = com.google.protobuf.descriptor.FileDescriptorProto.parseFrom(ProtoBytes)
    _root_.scalapb.descriptors.FileDescriptor.buildFrom(scalaProto, dependencies.map(_.scalaDescriptor))
  }
  lazy val javaDescriptor: com.google.protobuf.Descriptors.FileDescriptor = {
    val javaProto = com.google.protobuf.DescriptorProtos.FileDescriptorProto.parseFrom(ProtoBytes)
    com.google.protobuf.Descriptors.FileDescriptor.buildFrom(javaProto, _root_.scala.Array(
      com.google.protobuf.any.AnyProto.javaDescriptor
    ))
  }
  @deprecated("Use javaDescriptor instead. In a future version this will refer to scalaDescriptor.", "ScalaPB 0.5.47")
  def descriptor: com.google.protobuf.Descriptors.FileDescriptor = javaDescriptor
}