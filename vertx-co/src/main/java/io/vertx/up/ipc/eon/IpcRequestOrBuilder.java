// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: zero.envelop.proto

package io.vertx.up.ipc.eon;

public interface IpcRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:io.vertx.up.ipc.eon.IpcRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Response format
   * </pre>
   *
   * <code>.io.vertx.up.ipc.eon.em.Format response_format = 1;</code>
   */
  int getResponseFormatValue();
  /**
   * <pre>
   * Response format
   * </pre>
   *
   * <code>.io.vertx.up.ipc.eon.em.Format response_format = 1;</code>
   */
  io.vertx.up.ipc.eon.em.Format getResponseFormat();

  /**
   * <pre>
   * Response type
   * </pre>
   *
   * <code>.io.vertx.up.ipc.eon.em.Category response_category = 2;</code>
   */
  int getResponseCategoryValue();
  /**
   * <pre>
   * Response type
   * </pre>
   *
   * <code>.io.vertx.up.ipc.eon.em.Category response_category = 2;</code>
   */
  io.vertx.up.ipc.eon.em.Category getResponseCategory();

  /**
   * <pre>
   * Size
   * </pre>
   *
   * <code>int32 response_size = 3;</code>
   */
  int getResponseSize();

  /**
   * <pre>
   * Content of response
   * </pre>
   *
   * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 4;</code>
   */
  boolean hasEnvelop();
  /**
   * <pre>
   * Content of response
   * </pre>
   *
   * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 4;</code>
   */
  io.vertx.up.ipc.eon.IpcEnvelop getEnvelop();
  /**
   * <pre>
   * Content of response
   * </pre>
   *
   * <code>.io.vertx.up.ipc.eon.IpcEnvelop envelop = 4;</code>
   */
  io.vertx.up.ipc.eon.IpcEnvelopOrBuilder getEnvelopOrBuilder();

  /**
   * <pre>
   * Whether contains username
   * </pre>
   *
   * <code>bool is_client_id = 5;</code>
   */
  boolean getIsClientId();

  /**
   * <pre>
   * Whether contains scope
   * </pre>
   *
   * <code>bool is_oauth_scope = 6;</code>
   */
  boolean getIsOauthScope();

  /**
   * <pre>
   * Compression
   * </pre>
   *
   * <code>.io.vertx.up.ipc.eon.em.Compression algorithm = 7;</code>
   */
  int getAlgorithmValue();
  /**
   * <pre>
   * Compression
   * </pre>
   *
   * <code>.io.vertx.up.ipc.eon.em.Compression algorithm = 7;</code>
   */
  io.vertx.up.ipc.eon.em.Compression getAlgorithm();

  /**
   * <pre>
   * Status
   * </pre>
   *
   * <code>.io.vertx.up.ipc.eon.IpcStatus response_status = 8;</code>
   */
  boolean hasResponseStatus();
  /**
   * <pre>
   * Status
   * </pre>
   *
   * <code>.io.vertx.up.ipc.eon.IpcStatus response_status = 8;</code>
   */
  io.vertx.up.ipc.eon.IpcStatus getResponseStatus();
  /**
   * <pre>
   * Status
   * </pre>
   *
   * <code>.io.vertx.up.ipc.eon.IpcStatus response_status = 8;</code>
   */
  io.vertx.up.ipc.eon.IpcStatusOrBuilder getResponseStatusOrBuilder();
}
