// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: zero.envelop.proto

package io.vertx.up.ipc.eon;

public final class UpEnvelop {
  private UpEnvelop() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_vertx_up_ipc_eon_IpcContent_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_vertx_up_ipc_eon_IpcContent_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_vertx_up_ipc_eon_IpcEnvelop_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_vertx_up_ipc_eon_IpcEnvelop_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_vertx_up_ipc_eon_IpcRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_vertx_up_ipc_eon_IpcRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_vertx_up_ipc_eon_IpcResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_vertx_up_ipc_eon_IpcResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022zero.envelop.proto\022\023io.vertx.up.ipc.eo" +
      "n\032\017zero.enum.proto\032\021zero.status.proto\"\033\n" +
      "\nIpcContent\022\r\n\005value\030\001 \001(\t\"X\n\nIpcEnvelop" +
      "\022,\n\004type\030\001 \001(\0162\036.io.vertx.up.ipc.eon.em." +
      "Format\022\014\n\004body\030\002 \001(\t\022\016\n\006stream\030\003 \001(\014\"\352\002\n" +
      "\nIpcRequest\0227\n\017response_format\030\001 \001(\0162\036.i" +
      "o.vertx.up.ipc.eon.em.Format\022;\n\021response" +
      "_category\030\002 \001(\0162 .io.vertx.up.ipc.eon.em" +
      ".Category\022\025\n\rresponse_size\030\003 \001(\005\0220\n\007enve" +
      "lop\030\004 \001(\0132\037.io.vertx.up.ipc.eon.IpcEnvel" +
      "op\022\024\n\014is_client_id\030\005 \001(\010\022\026\n\016is_oauth_sco" +
      "pe\030\006 \001(\010\0226\n\talgorithm\030\007 \001(\0162#.io.vertx.u" +
      "p.ipc.eon.em.Compression\0227\n\017response_sta" +
      "tus\030\010 \001(\0132\036.io.vertx.up.ipc.eon.IpcStatu" +
      "s\"g\n\013IpcResponse\0220\n\007envelop\030\001 \001(\0132\037.io.v" +
      "ertx.up.ipc.eon.IpcEnvelop\022\021\n\tclient_id\030" +
      "\002 \001(\t\022\023\n\013oauth_scope\030\003 \001(\tB\"\n\023io.vertx.u" +
      "p.ipc.eonB\tUpEnvelopP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          io.vertx.up.ipc.eon.em.UpEnum.getDescriptor(),
          io.vertx.up.ipc.eon.UpStatus.getDescriptor(),
        }, assigner);
    internal_static_io_vertx_up_ipc_eon_IpcContent_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_io_vertx_up_ipc_eon_IpcContent_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_vertx_up_ipc_eon_IpcContent_descriptor,
        new java.lang.String[] { "Value", });
    internal_static_io_vertx_up_ipc_eon_IpcEnvelop_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_io_vertx_up_ipc_eon_IpcEnvelop_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_vertx_up_ipc_eon_IpcEnvelop_descriptor,
        new java.lang.String[] { "Type", "Body", "Stream", });
    internal_static_io_vertx_up_ipc_eon_IpcRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_io_vertx_up_ipc_eon_IpcRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_vertx_up_ipc_eon_IpcRequest_descriptor,
        new java.lang.String[] { "ResponseFormat", "ResponseCategory", "ResponseSize", "Envelop", "IsClientId", "IsOauthScope", "Algorithm", "ResponseStatus", });
    internal_static_io_vertx_up_ipc_eon_IpcResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_io_vertx_up_ipc_eon_IpcResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_vertx_up_ipc_eon_IpcResponse_descriptor,
        new java.lang.String[] { "Envelop", "ClientId", "OauthScope", });
    io.vertx.up.ipc.eon.em.UpEnum.getDescriptor();
    io.vertx.up.ipc.eon.UpStatus.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
