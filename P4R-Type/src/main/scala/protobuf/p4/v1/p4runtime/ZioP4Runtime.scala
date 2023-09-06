package p4.v1.p4runtime

import scala.language.implicitConversions

object ZioP4Runtime {
  trait ZP4Runtime[-R, -Context] extends scalapb.zio_grpc.ZGeneratedService[R, Context, ZP4Runtime] {
    self =>
    def write(request: p4.v1.p4runtime.WriteRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, p4.v1.p4runtime.WriteResponse]
    def read(request: p4.v1.p4runtime.ReadRequest): _root_.zio.stream.ZStream[R with Context, io.grpc.Status, p4.v1.p4runtime.ReadResponse]
    def setForwardingPipelineConfig(request: p4.v1.p4runtime.SetForwardingPipelineConfigRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, p4.v1.p4runtime.SetForwardingPipelineConfigResponse]
    def getForwardingPipelineConfig(request: p4.v1.p4runtime.GetForwardingPipelineConfigRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, p4.v1.p4runtime.GetForwardingPipelineConfigResponse]
    def streamChannel(request: _root_.zio.stream.Stream[io.grpc.Status, p4.v1.p4runtime.StreamMessageRequest]): _root_.zio.stream.ZStream[R with Context, io.grpc.Status, p4.v1.p4runtime.StreamMessageResponse]
    def capabilities(request: p4.v1.p4runtime.CapabilitiesRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, p4.v1.p4runtime.CapabilitiesResponse]
  }
  type P4Runtime = ZP4Runtime[Any, Any]
  type RP4Runtime[R] = ZP4Runtime[R, Any]
  type RCP4Runtime[R] = ZP4Runtime[R, scalapb.zio_grpc.RequestContext]

  object ZP4Runtime {
    implicit val transformableService: scalapb.zio_grpc.TransformableService[ZP4Runtime] = new scalapb.zio_grpc.TransformableService[ZP4Runtime] {
      def transform[R, Context, R1, Context1](self: ZP4Runtime[R, Context], f: scalapb.zio_grpc.ZTransform[R with Context, io.grpc.Status, R1 with Context1]): p4.v1.p4runtime.ZioP4Runtime.ZP4Runtime[R1, Context1] = new p4.v1.p4runtime.ZioP4Runtime.ZP4Runtime[R1, Context1] {
        def write(request: p4.v1.p4runtime.WriteRequest): _root_.zio.ZIO[R1 with Context1, io.grpc.Status, p4.v1.p4runtime.WriteResponse] = f.effect(self.write(request))
        def read(request: p4.v1.p4runtime.ReadRequest): _root_.zio.stream.ZStream[R1 with Context1, io.grpc.Status, p4.v1.p4runtime.ReadResponse] = f.stream(self.read(request))
        def setForwardingPipelineConfig(request: p4.v1.p4runtime.SetForwardingPipelineConfigRequest): _root_.zio.ZIO[R1 with Context1, io.grpc.Status, p4.v1.p4runtime.SetForwardingPipelineConfigResponse] = f.effect(self.setForwardingPipelineConfig(request))
        def getForwardingPipelineConfig(request: p4.v1.p4runtime.GetForwardingPipelineConfigRequest): _root_.zio.ZIO[R1 with Context1, io.grpc.Status, p4.v1.p4runtime.GetForwardingPipelineConfigResponse] = f.effect(self.getForwardingPipelineConfig(request))
        def streamChannel(request: _root_.zio.stream.Stream[io.grpc.Status, p4.v1.p4runtime.StreamMessageRequest]): _root_.zio.stream.ZStream[R1 with Context1, io.grpc.Status, p4.v1.p4runtime.StreamMessageResponse] = f.stream(self.streamChannel(request))
        def capabilities(request: p4.v1.p4runtime.CapabilitiesRequest): _root_.zio.ZIO[R1 with Context1, io.grpc.Status, p4.v1.p4runtime.CapabilitiesResponse] = f.effect(self.capabilities(request))
      }
    }
    implicit def ops[R, C](service: p4.v1.p4runtime.ZioP4Runtime.ZP4Runtime[R, C]): scalapb.zio_grpc.TransformableService.TransformableServiceOps[p4.v1.p4runtime.ZioP4Runtime.ZP4Runtime, R, C] = new scalapb.zio_grpc.TransformableService.TransformableServiceOps[p4.v1.p4runtime.ZioP4Runtime.ZP4Runtime, R, C](service)
    implicit val genericBindable: scalapb.zio_grpc.GenericBindable[p4.v1.p4runtime.ZioP4Runtime.ZP4Runtime] = new scalapb.zio_grpc.GenericBindable[p4.v1.p4runtime.ZioP4Runtime.ZP4Runtime] {
      def bind[R](serviceImpl: p4.v1.p4runtime.ZioP4Runtime.ZP4Runtime[R, scalapb.zio_grpc.RequestContext]): zio.URIO[R, _root_.io.grpc.ServerServiceDefinition] =
        zio.ZIO.runtime[Any].flatMap {
          runtime =>
            zio.ZIO.environmentWith[R] { (env: zio.ZEnvironment[R]) =>
              _root_.io.grpc.ServerServiceDefinition.builder(p4.v1.p4runtime.P4RuntimeGrpc.SERVICE)
              .addMethod(
                p4.v1.p4runtime.P4RuntimeGrpc.METHOD_WRITE,
                _root_.scalapb.zio_grpc.server.ZServerCallHandler.unaryCallHandler(runtime, (t: p4.v1.p4runtime.WriteRequest)=>serviceImpl.write(t).provideSomeEnvironment((rc: zio.ZEnvironment[scalapb.zio_grpc.RequestContext]) => env.union[scalapb.zio_grpc.RequestContext](rc)))
              )
              .addMethod(
                p4.v1.p4runtime.P4RuntimeGrpc.METHOD_READ,
                _root_.scalapb.zio_grpc.server.ZServerCallHandler.serverStreamingCallHandler(runtime, (t: p4.v1.p4runtime.ReadRequest)=>serviceImpl.read(t).provideSomeEnvironment((rc: zio.ZEnvironment[scalapb.zio_grpc.RequestContext]) => env.union[scalapb.zio_grpc.RequestContext](rc)))
              )
              .addMethod(
                p4.v1.p4runtime.P4RuntimeGrpc.METHOD_SET_FORWARDING_PIPELINE_CONFIG,
                _root_.scalapb.zio_grpc.server.ZServerCallHandler.unaryCallHandler(runtime, (t: p4.v1.p4runtime.SetForwardingPipelineConfigRequest)=>serviceImpl.setForwardingPipelineConfig(t).provideSomeEnvironment((rc: zio.ZEnvironment[scalapb.zio_grpc.RequestContext]) => env.union[scalapb.zio_grpc.RequestContext](rc)))
              )
              .addMethod(
                p4.v1.p4runtime.P4RuntimeGrpc.METHOD_GET_FORWARDING_PIPELINE_CONFIG,
                _root_.scalapb.zio_grpc.server.ZServerCallHandler.unaryCallHandler(runtime, (t: p4.v1.p4runtime.GetForwardingPipelineConfigRequest)=>serviceImpl.getForwardingPipelineConfig(t).provideSomeEnvironment((rc: zio.ZEnvironment[scalapb.zio_grpc.RequestContext]) => env.union[scalapb.zio_grpc.RequestContext](rc)))
              )
              .addMethod(
                p4.v1.p4runtime.P4RuntimeGrpc.METHOD_STREAM_CHANNEL,
                _root_.scalapb.zio_grpc.server.ZServerCallHandler.bidiCallHandler(runtime, (t: _root_.zio.stream.Stream[io.grpc.Status, p4.v1.p4runtime.StreamMessageRequest])=>serviceImpl.streamChannel(t).provideSomeEnvironment((rc: zio.ZEnvironment[scalapb.zio_grpc.RequestContext]) => env.union[scalapb.zio_grpc.RequestContext](rc)))
              )
              .addMethod(
                p4.v1.p4runtime.P4RuntimeGrpc.METHOD_CAPABILITIES,
                _root_.scalapb.zio_grpc.server.ZServerCallHandler.unaryCallHandler(runtime, (t: p4.v1.p4runtime.CapabilitiesRequest)=>serviceImpl.capabilities(t).provideSomeEnvironment((rc: zio.ZEnvironment[scalapb.zio_grpc.RequestContext]) => env.union[scalapb.zio_grpc.RequestContext](rc)))
              )
              .build()
            }
        }
      }
  }

  type P4RuntimeClient = P4RuntimeClient.Service

  // accessor methods
  class P4RuntimeAccessors[Context: zio.Tag](callOptions: zio.IO[io.grpc.Status, io.grpc.CallOptions]) extends scalapb.zio_grpc.CallOptionsMethods[P4RuntimeAccessors[Context]] {
    def this() = this(zio.ZIO.succeed(io.grpc.CallOptions.DEFAULT))
    def write(request: p4.v1.p4runtime.WriteRequest): _root_.zio.ZIO[P4RuntimeClient.ZService[Any, Context] with Context, io.grpc.Status, p4.v1.p4runtime.WriteResponse] = _root_.zio.ZIO.serviceWithZIO[P4RuntimeClient.ZService[Any, Context]](_.withCallOptionsZIO(callOptions).write(request))
    def read(request: p4.v1.p4runtime.ReadRequest): _root_.zio.stream.ZStream[P4RuntimeClient.ZService[Any, Context] with Context, io.grpc.Status, p4.v1.p4runtime.ReadResponse] = _root_.zio.stream.ZStream.serviceWithStream[P4RuntimeClient.ZService[Any, Context]](_.withCallOptionsZIO(callOptions).read(request))
    def setForwardingPipelineConfig(request: p4.v1.p4runtime.SetForwardingPipelineConfigRequest): _root_.zio.ZIO[P4RuntimeClient.ZService[Any, Context] with Context, io.grpc.Status, p4.v1.p4runtime.SetForwardingPipelineConfigResponse] = _root_.zio.ZIO.serviceWithZIO[P4RuntimeClient.ZService[Any, Context]](_.withCallOptionsZIO(callOptions).setForwardingPipelineConfig(request))
    def getForwardingPipelineConfig(request: p4.v1.p4runtime.GetForwardingPipelineConfigRequest): _root_.zio.ZIO[P4RuntimeClient.ZService[Any, Context] with Context, io.grpc.Status, p4.v1.p4runtime.GetForwardingPipelineConfigResponse] = _root_.zio.ZIO.serviceWithZIO[P4RuntimeClient.ZService[Any, Context]](_.withCallOptionsZIO(callOptions).getForwardingPipelineConfig(request))
    def streamChannel[R0: zio.Tag](request: _root_.zio.stream.ZStream[R0, io.grpc.Status, p4.v1.p4runtime.StreamMessageRequest]): _root_.zio.stream.ZStream[P4RuntimeClient.ZService[Any, Context] with Context with R0, io.grpc.Status, p4.v1.p4runtime.StreamMessageResponse] = _root_.zio.stream.ZStream.serviceWithStream[P4RuntimeClient.ZService[Any, Context]](_.withCallOptionsZIO(callOptions).streamChannel(request))
    def capabilities(request: p4.v1.p4runtime.CapabilitiesRequest): _root_.zio.ZIO[P4RuntimeClient.ZService[Any, Context] with Context, io.grpc.Status, p4.v1.p4runtime.CapabilitiesResponse] = _root_.zio.ZIO.serviceWithZIO[P4RuntimeClient.ZService[Any, Context]](_.withCallOptionsZIO(callOptions).capabilities(request))
    def mapCallOptionsZIO(f: io.grpc.CallOptions => zio.IO[io.grpc.Status, io.grpc.CallOptions]) = new P4RuntimeAccessors(callOptions.flatMap(f))
  }

  object P4RuntimeClient extends P4RuntimeAccessors[Any](zio.ZIO.succeed(io.grpc.CallOptions.DEFAULT)) {
    trait ZService[R, Context] extends scalapb.zio_grpc.CallOptionsMethods[ZService[R, Context]] {
      def write(request: p4.v1.p4runtime.WriteRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, p4.v1.p4runtime.WriteResponse]
      def read(request: p4.v1.p4runtime.ReadRequest): _root_.zio.stream.ZStream[R with Context, io.grpc.Status, p4.v1.p4runtime.ReadResponse]
      def setForwardingPipelineConfig(request: p4.v1.p4runtime.SetForwardingPipelineConfigRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, p4.v1.p4runtime.SetForwardingPipelineConfigResponse]
      def getForwardingPipelineConfig(request: p4.v1.p4runtime.GetForwardingPipelineConfigRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, p4.v1.p4runtime.GetForwardingPipelineConfigResponse]
      def streamChannel[R0](request: _root_.zio.stream.ZStream[R0, io.grpc.Status, p4.v1.p4runtime.StreamMessageRequest]): _root_.zio.stream.ZStream[R with Context with R0, io.grpc.Status, p4.v1.p4runtime.StreamMessageResponse]
      def capabilities(request: p4.v1.p4runtime.CapabilitiesRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, p4.v1.p4runtime.CapabilitiesResponse]

      // Returns a copy of the service with new default metadata
      def withMetadataZIO[C](headersEffect: zio.ZIO[C, io.grpc.Status, scalapb.zio_grpc.SafeMetadata]): ZService[R, C]
      def withCallOptionsZIO(callOptions: zio.IO[io.grpc.Status, io.grpc.CallOptions]): ZService[R, Context]
    }
    type Service = ZService[Any, Any]
    type Accessors[Context] = p4.v1.p4runtime.ZioP4Runtime.P4RuntimeAccessors[Context]
    implicit val transformableService: scalapb.zio_grpc.TransformableService[ZService] = new scalapb.zio_grpc.TransformableService[ZService] {
      def transform[R, Context, R1, Context1](self: ZService[R, Context], f: scalapb.zio_grpc.ZTransform[R with Context, io.grpc.Status, R1 with Context1]): ZService[R1, Context1] = new ZService[R1, Context1] {
        def write(request: p4.v1.p4runtime.WriteRequest): _root_.zio.ZIO[R1 with Context1, io.grpc.Status, p4.v1.p4runtime.WriteResponse] = f.effect(self.write(request))
        def read(request: p4.v1.p4runtime.ReadRequest): _root_.zio.stream.ZStream[R1 with Context1, io.grpc.Status, p4.v1.p4runtime.ReadResponse] = f.stream(self.read(request))
        def setForwardingPipelineConfig(request: p4.v1.p4runtime.SetForwardingPipelineConfigRequest): _root_.zio.ZIO[R1 with Context1, io.grpc.Status, p4.v1.p4runtime.SetForwardingPipelineConfigResponse] = f.effect(self.setForwardingPipelineConfig(request))
        def getForwardingPipelineConfig(request: p4.v1.p4runtime.GetForwardingPipelineConfigRequest): _root_.zio.ZIO[R1 with Context1, io.grpc.Status, p4.v1.p4runtime.GetForwardingPipelineConfigResponse] = f.effect(self.getForwardingPipelineConfig(request))
        def streamChannel[RR](request: _root_.zio.stream.ZStream[RR, io.grpc.Status, p4.v1.p4runtime.StreamMessageRequest]): _root_.zio.stream.ZStream[R1 with Context1 with RR, io.grpc.Status, p4.v1.p4runtime.StreamMessageResponse] = zio.stream.ZStream.fail(io.grpc.Status.INTERNAL.withDescription("Transforming client-side bidi calls is not supported"))
        def capabilities(request: p4.v1.p4runtime.CapabilitiesRequest): _root_.zio.ZIO[R1 with Context1, io.grpc.Status, p4.v1.p4runtime.CapabilitiesResponse] = f.effect(self.capabilities(request))
        // Returns a copy of the service with new default metadata
        def mapCallOptionsZIO(cf: io.grpc.CallOptions => zio.IO[io.grpc.Status, io.grpc.CallOptions]): ZService[R1, Context1] = transform[R, Context, R1, Context1](self.mapCallOptionsZIO(cf), f)
        def withMetadataZIO[C](headersEffect: zio.ZIO[C, io.grpc.Status, scalapb.zio_grpc.SafeMetadata]): ZService[R1, C] = ??? // transform[R, Context, R1, C](self.withMetadataZIO(headersEffect), f)
        def withCallOptionsZIO(callOptions: zio.IO[io.grpc.Status, io.grpc.CallOptions]): ZService[R1, Context1] = transform[R, Context, R1, Context1](self.withCallOptionsZIO(callOptions), f)
      }
    }
    implicit def ops[R, C](service: ZService[R, C]): scalapb.zio_grpc.TransformableService.TransformableServiceOps[ZService, R, C] = new scalapb.zio_grpc.TransformableService.TransformableServiceOps[ZService, R, C](service)


    private[this] class ServiceStub[R, Context](channel: scalapb.zio_grpc.ZChannel[R], options: zio.IO[io.grpc.Status, io.grpc.CallOptions], headers: zio.ZIO[Context, io.grpc.Status, scalapb.zio_grpc.SafeMetadata])
        extends P4RuntimeClient.ZService[R, Context] {
      def write(request: p4.v1.p4runtime.WriteRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, p4.v1.p4runtime.WriteResponse] = headers.zip(options).flatMap { case (headers, options) => scalapb.zio_grpc.client.ClientCalls.unaryCall(
        channel, p4.v1.p4runtime.P4RuntimeGrpc.METHOD_WRITE, options,
        headers,
        request
      )}
      def read(request: p4.v1.p4runtime.ReadRequest): _root_.zio.stream.ZStream[R with Context, io.grpc.Status, p4.v1.p4runtime.ReadResponse] = zio.stream.ZStream.fromZIO(headers.zip(options)).flatMap { case (headers, options) => scalapb.zio_grpc.client.ClientCalls.serverStreamingCall(
        channel, p4.v1.p4runtime.P4RuntimeGrpc.METHOD_READ, options,
        headers,
        request
      )}
      def setForwardingPipelineConfig(request: p4.v1.p4runtime.SetForwardingPipelineConfigRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, p4.v1.p4runtime.SetForwardingPipelineConfigResponse] = headers.zip(options).flatMap { case (headers, options) => scalapb.zio_grpc.client.ClientCalls.unaryCall(
        channel, p4.v1.p4runtime.P4RuntimeGrpc.METHOD_SET_FORWARDING_PIPELINE_CONFIG, options,
        headers,
        request
      )}
      def getForwardingPipelineConfig(request: p4.v1.p4runtime.GetForwardingPipelineConfigRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, p4.v1.p4runtime.GetForwardingPipelineConfigResponse] = headers.zip(options).flatMap { case (headers, options) => scalapb.zio_grpc.client.ClientCalls.unaryCall(
        channel, p4.v1.p4runtime.P4RuntimeGrpc.METHOD_GET_FORWARDING_PIPELINE_CONFIG, options,
        headers,
        request
      )}
      def streamChannel[R0](request: _root_.zio.stream.ZStream[R0, io.grpc.Status, p4.v1.p4runtime.StreamMessageRequest]): _root_.zio.stream.ZStream[R with Context with R0, io.grpc.Status, p4.v1.p4runtime.StreamMessageResponse] = zio.stream.ZStream.fromZIO(headers.zip(options)).flatMap { case (headers, options) => scalapb.zio_grpc.client.ClientCalls.bidiCall(
        channel, p4.v1.p4runtime.P4RuntimeGrpc.METHOD_STREAM_CHANNEL, options,
        headers,
        request
      )}
      def capabilities(request: p4.v1.p4runtime.CapabilitiesRequest): _root_.zio.ZIO[R with Context, io.grpc.Status, p4.v1.p4runtime.CapabilitiesResponse] = headers.zip(options).flatMap { case (headers, options) => scalapb.zio_grpc.client.ClientCalls.unaryCall(
        channel, p4.v1.p4runtime.P4RuntimeGrpc.METHOD_CAPABILITIES, options,
        headers,
        request
      )}
      def mapCallOptionsZIO(f: io.grpc.CallOptions => zio.IO[io.grpc.Status, io.grpc.CallOptions]): ZService[R, Context] = new ServiceStub(channel, options.flatMap(f), headers)
      override def withMetadataZIO[C](headersEffect: zio.ZIO[C, io.grpc.Status, scalapb.zio_grpc.SafeMetadata]): ZService[R, C] = new ServiceStub(channel, options, headersEffect)
      def withCallOptionsZIO(callOptions: zio.IO[io.grpc.Status, io.grpc.CallOptions]): ZService[R, Context] = new ServiceStub(channel, callOptions, headers)
    }

    def scoped[R, Context](managedChannel: scalapb.zio_grpc.ZManagedChannel[R], options: zio.IO[io.grpc.Status, io.grpc.CallOptions] = zio.ZIO.succeed(io.grpc.CallOptions.DEFAULT), headers: zio.ZIO[Context, io.grpc.Status, scalapb.zio_grpc.SafeMetadata]=scalapb.zio_grpc.SafeMetadata.make): zio.ZIO[zio.Scope, Throwable, P4RuntimeClient.ZService[R, Context]] = managedChannel.map {
      channel => new ServiceStub[R, Context](channel, options, headers)
    }

    def live[R, Context: zio.Tag](managedChannel: scalapb.zio_grpc.ZManagedChannel[R], options: zio.IO[io.grpc.Status, io.grpc.CallOptions]=zio.ZIO.succeed(io.grpc.CallOptions.DEFAULT), headers: zio.ZIO[Context, io.grpc.Status, scalapb.zio_grpc.SafeMetadata] = scalapb.zio_grpc.SafeMetadata.make): zio.ZLayer[R, Throwable, P4RuntimeClient.ZService[Any, Context]] = zio.ZLayer.scoped[R](zio.ZIO.environmentWithZIO((r: zio.ZEnvironment[R]) => scoped[Any, Context](managedChannel.map(_.provideEnvironment(r)), options, headers)))
  }
}