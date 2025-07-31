package generated.healthwellbeing;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: HealthWellBeing.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HealthWellBeingGrpc {

  private HealthWellBeingGrpc() {}

  public static final String SERVICE_NAME = "HealthWellBeing.HealthWellBeing";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.healthwellbeing.BodyScanner,
      generated.healthwellbeing.Results> getMonitorTheBodyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MonitorTheBody",
      requestType = generated.healthwellbeing.BodyScanner.class,
      responseType = generated.healthwellbeing.Results.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<generated.healthwellbeing.BodyScanner,
      generated.healthwellbeing.Results> getMonitorTheBodyMethod() {
    io.grpc.MethodDescriptor<generated.healthwellbeing.BodyScanner, generated.healthwellbeing.Results> getMonitorTheBodyMethod;
    if ((getMonitorTheBodyMethod = HealthWellBeingGrpc.getMonitorTheBodyMethod) == null) {
      synchronized (HealthWellBeingGrpc.class) {
        if ((getMonitorTheBodyMethod = HealthWellBeingGrpc.getMonitorTheBodyMethod) == null) {
          HealthWellBeingGrpc.getMonitorTheBodyMethod = getMonitorTheBodyMethod =
              io.grpc.MethodDescriptor.<generated.healthwellbeing.BodyScanner, generated.healthwellbeing.Results>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MonitorTheBody"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.healthwellbeing.BodyScanner.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.healthwellbeing.Results.getDefaultInstance()))
              .setSchemaDescriptor(new HealthWellBeingMethodDescriptorSupplier("MonitorTheBody"))
              .build();
        }
      }
    }
    return getMonitorTheBodyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.healthwellbeing.Requests,
      generated.healthwellbeing.Advices> getAdviceNewBornMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AdviceNewBorn",
      requestType = generated.healthwellbeing.Requests.class,
      responseType = generated.healthwellbeing.Advices.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<generated.healthwellbeing.Requests,
      generated.healthwellbeing.Advices> getAdviceNewBornMethod() {
    io.grpc.MethodDescriptor<generated.healthwellbeing.Requests, generated.healthwellbeing.Advices> getAdviceNewBornMethod;
    if ((getAdviceNewBornMethod = HealthWellBeingGrpc.getAdviceNewBornMethod) == null) {
      synchronized (HealthWellBeingGrpc.class) {
        if ((getAdviceNewBornMethod = HealthWellBeingGrpc.getAdviceNewBornMethod) == null) {
          HealthWellBeingGrpc.getAdviceNewBornMethod = getAdviceNewBornMethod =
              io.grpc.MethodDescriptor.<generated.healthwellbeing.Requests, generated.healthwellbeing.Advices>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AdviceNewBorn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.healthwellbeing.Requests.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.healthwellbeing.Advices.getDefaultInstance()))
              .setSchemaDescriptor(new HealthWellBeingMethodDescriptorSupplier("AdviceNewBorn"))
              .build();
        }
      }
    }
    return getAdviceNewBornMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HealthWellBeingStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HealthWellBeingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HealthWellBeingStub>() {
        @java.lang.Override
        public HealthWellBeingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HealthWellBeingStub(channel, callOptions);
        }
      };
    return HealthWellBeingStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HealthWellBeingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HealthWellBeingBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HealthWellBeingBlockingStub>() {
        @java.lang.Override
        public HealthWellBeingBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HealthWellBeingBlockingStub(channel, callOptions);
        }
      };
    return HealthWellBeingBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HealthWellBeingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HealthWellBeingFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HealthWellBeingFutureStub>() {
        @java.lang.Override
        public HealthWellBeingFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HealthWellBeingFutureStub(channel, callOptions);
        }
      };
    return HealthWellBeingFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class HealthWellBeingImplBase implements io.grpc.BindableService {

    /**
     */
    public void monitorTheBody(generated.healthwellbeing.BodyScanner request,
        io.grpc.stub.StreamObserver<generated.healthwellbeing.Results> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMonitorTheBodyMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.healthwellbeing.Requests> adviceNewBorn(
        io.grpc.stub.StreamObserver<generated.healthwellbeing.Advices> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getAdviceNewBornMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMonitorTheBodyMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                generated.healthwellbeing.BodyScanner,
                generated.healthwellbeing.Results>(
                  this, METHODID_MONITOR_THE_BODY)))
          .addMethod(
            getAdviceNewBornMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                generated.healthwellbeing.Requests,
                generated.healthwellbeing.Advices>(
                  this, METHODID_ADVICE_NEW_BORN)))
          .build();
    }
  }

  /**
   */
  public static final class HealthWellBeingStub extends io.grpc.stub.AbstractAsyncStub<HealthWellBeingStub> {
    private HealthWellBeingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HealthWellBeingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HealthWellBeingStub(channel, callOptions);
    }

    /**
     */
    public void monitorTheBody(generated.healthwellbeing.BodyScanner request,
        io.grpc.stub.StreamObserver<generated.healthwellbeing.Results> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getMonitorTheBodyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.healthwellbeing.Requests> adviceNewBorn(
        io.grpc.stub.StreamObserver<generated.healthwellbeing.Advices> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getAdviceNewBornMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class HealthWellBeingBlockingStub extends io.grpc.stub.AbstractBlockingStub<HealthWellBeingBlockingStub> {
    private HealthWellBeingBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HealthWellBeingBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HealthWellBeingBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<generated.healthwellbeing.Results> monitorTheBody(
        generated.healthwellbeing.BodyScanner request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getMonitorTheBodyMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HealthWellBeingFutureStub extends io.grpc.stub.AbstractFutureStub<HealthWellBeingFutureStub> {
    private HealthWellBeingFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HealthWellBeingFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HealthWellBeingFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_MONITOR_THE_BODY = 0;
  private static final int METHODID_ADVICE_NEW_BORN = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HealthWellBeingImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HealthWellBeingImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MONITOR_THE_BODY:
          serviceImpl.monitorTheBody((generated.healthwellbeing.BodyScanner) request,
              (io.grpc.stub.StreamObserver<generated.healthwellbeing.Results>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADVICE_NEW_BORN:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.adviceNewBorn(
              (io.grpc.stub.StreamObserver<generated.healthwellbeing.Advices>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HealthWellBeingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HealthWellBeingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.healthwellbeing.HealthWellBeingServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HealthWellBeing");
    }
  }

  private static final class HealthWellBeingFileDescriptorSupplier
      extends HealthWellBeingBaseDescriptorSupplier {
    HealthWellBeingFileDescriptorSupplier() {}
  }

  private static final class HealthWellBeingMethodDescriptorSupplier
      extends HealthWellBeingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HealthWellBeingMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HealthWellBeingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HealthWellBeingFileDescriptorSupplier())
              .addMethod(getMonitorTheBodyMethod())
              .addMethod(getAdviceNewBornMethod())
              .build();
        }
      }
    }
    return result;
  }
}
